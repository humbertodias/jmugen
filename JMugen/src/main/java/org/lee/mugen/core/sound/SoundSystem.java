/*
 * Created on 27 mars 2004
 *
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.lee.mugen.core.sound;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.lee.mugen.audio.adx.sample.convert.Adx;
import org.lee.mugen.audio.adx.sample.convert.AdxDecoder;

/**
 * Play sound, and fx Sound
 * This class play Wav, MP3 in pure java code
 * TODO : replace with plugin
 * @author Dr Wong
 *
 */
public final class SoundSystem {
    
    
    public static final class SoundBackGround implements Runnable {
        
        static {
            try {
                Class.forName("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
                Class.forName("javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider");

//                Class.forName("javazoom.spi.vorbis.sampled.file.VorbisAudioFileFormat");
//                Class.forName("javazoom.spi.vorbis.sampled.convert.VorbisFormatConversionProvider");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        public static final int _BUFFER = 1024 * 256;
        private AudioInputStream stream;
        private AdxDecoder adxDecoder;
        private SourceDataLine _srcDataLine = null;
        private String file;
        private boolean stop = false;
        private boolean loop = false;
        private int _frameBufferSize;
        
        
        private static SoundBackGround soundsys;
        
        /**
         * @return
         */        
        public static boolean isStop() {
            return soundsys.stop;
        }
        
        /**
         * @param path
         */        
        public static void playMusic(final String path) {
        	stopMusic();
        	if (!new File(path).exists() || new File(path).isDirectory())
        		return;
            soundsys = new SoundBackGround(path);
            soundsys.setloop(true);

            Thread ts = new Thread(soundsys);
            ts.setDaemon(true);
            ts.start();
        }
        public static void setVolume(final float volume) {
        	FloatControl control = (FloatControl) soundsys.getSrcDataLine().getControl(FloatControl.Type.VOLUME);
        	control.setValue(volume);
        }
        public static float getVolume() {
        	FloatControl control = (FloatControl) soundsys.getSrcDataLine().getControl(FloatControl.Type.VOLUME);
        	return control.getValue();
        }
        
        public static void stopMusic() {
        	if (soundsys != null)
        		soundsys.stopPlay();
        }
        
        /**
         * @param name
         */        
        public SoundBackGround(String name) {
            file = name;
        }
        public void stopPlay() {
            getSrcDataLine().stop();
            getSrcDataLine().close();
            try {
            	if (stream != null)
            		stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stop = true;
        }
        /**
         * @param b
         */        
        public void setloop(boolean b) {
            loop = b;
        }
        public void run() {
            if (loop) {
                while (!stop) {
                    getSound(file);
                    if( getSrcDataLine().isControlSupported( FloatControl.Type.MASTER_GAIN ) ) {
                        FloatControl volume = (FloatControl) getSrcDataLine().getControl( FloatControl.Type.MASTER_GAIN );
                        volume.setValue(-5);
                     }
                    playSound();
                }
                getSrcDataLine().stop();
                getSrcDataLine().close();
                _srcDataLine.flush();
            } else {
                getSound(file);
                playSound();
                getSrcDataLine().stop();
                getSrcDataLine().close();
                _srcDataLine.flush();
            }
            getSrcDataLine().stop();
        }
        private SourceDataLine getSrcDataLine() {
            return _srcDataLine;
        }
        
        private void setSrcDataLine(SourceDataLine srcDataLine) {
            _srcDataLine = srcDataLine;
        }
        final int BUFFER_SIZE = 1024;
        private byte[] streamRead() throws IOException {
        	if (stream != null) {
                byte[] data = new byte[BUFFER_SIZE];
                int byteRead = stream.read(data, 0, BUFFER_SIZE);
                if (byteRead == BUFFER_SIZE) {
                	return data;
                } else {
                	byte[] data2 = new byte[byteRead];
                	System.arraycopy(data, 0, data2, 0, byteRead);
                	return data2;
                }
        	} else {
        		return adxDecoder.read(2048);
        	}
        }
        private boolean isEndStream(byte[] bytes) {
        	if (stream != null)
        		return BUFFER_SIZE > bytes.length;
        	return bytes.length == 0;
        }
        private void streamClose() throws IOException {
        	if (stream != null) {
        		stream.close();
        	} else {
        		adxDecoder.close();
        	}
        }
        private void playSound() {

            try {
                byte[] bytes = null;
                while (!stop) {
                	bytes = streamRead();
                	if (bytes.length > 0) {
//                		int step = 5;
//                		for (int i = 0; i < bytes.length && !stop; i+=step)
//            			getSrcDataLine().write(bytes, i, Math.min(step, bytes.length - i));
            			getSrcDataLine().write(bytes, 0, bytes.length);
                	}
                    if (isEndStream(bytes))
                    	break;
                    
                }
                getSrcDataLine().drain();
                streamClose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        /**
         * @param fileName
         * @throws Exception 
         */  
        public void getSound(String fileName) {
        	try {
            	if (fileName.toLowerCase().endsWith(".adx") || fileName.toLowerCase().endsWith(".bin")) {
            		getSoundAdx(fileName);
            	} else {
            		getSoundWavMp3(fileName);
            	}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        public void getSoundAdx(String fileName) throws Exception {
        	adxDecoder = new AdxDecoder(new Adx(new File(fileName)));
            AudioFormat decodedFormat =
                new AudioFormat(
	                AudioFormat.Encoding.PCM_SIGNED,
	                44100.0f,
	                16,
	                2,
	                2 * 2,
	                44100.0f,
	                false);
            DataLine.Info outInfo = new DataLine.Info(SourceDataLine.class, decodedFormat);
            if (!AudioSystem.isLineSupported(outInfo)) {
                throw new Exception("this format is not supported.");
            }
            _srcDataLine = (SourceDataLine) AudioSystem.getLine(outInfo);
            _srcDataLine.open(decodedFormat, _BUFFER);
            _srcDataLine.start();
        }

        public void getSoundWavMp3(String fileName) throws Exception {
//            AudioFileFormat aff = AudioSystem.getAudioFileFormat(new File(fileName));
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(fileName));
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat =
			                new AudioFormat(
			                AudioFormat.Encoding.PCM_SIGNED,
			                baseFormat.getSampleRate(),
			                16,
			                baseFormat.getChannels(),
			                baseFormat.getChannels() * 2,
			                baseFormat.getSampleRate(),
			                false);
            stream = AudioSystem.getAudioInputStream(decodedFormat, in);
            
            if ((decodedFormat.getEncoding() == AudioFormat.Encoding.ULAW)
            || (decodedFormat.getEncoding()
            == AudioFormat.Encoding.ALAW)) {
                AudioFormat tmpFormat =
                new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                decodedFormat.getSampleRate(),
                decodedFormat.getSampleSizeInBits(),
                decodedFormat.getChannels(),
                decodedFormat.getFrameSize(),
                decodedFormat.getFrameRate(),
                true);
                stream = AudioSystem.getAudioInputStream(tmpFormat, stream);
                decodedFormat = tmpFormat;
            }
            
            if (_srcDataLine == null) {
                DataLine.Info outInfo = new DataLine.Info(SourceDataLine.class, decodedFormat);
                if (!AudioSystem.isLineSupported(outInfo)) {
                    throw new Exception("this format is not supported.");
                }
                _srcDataLine = (SourceDataLine) AudioSystem.getLine(outInfo);
                _srcDataLine.open(decodedFormat, _BUFFER);
                _srcDataLine.start();
            }
            int frameSizeInBytes = decodedFormat.getFrameSize();
            _frameBufferSize -= _BUFFER % frameSizeInBytes;
        }
    }
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, InterruptedException {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		FileInputStream fis = new FileInputStream("c:/temp/9.wav");
//		
//		IOUtils.copy(fis, baos);
//		fis.close();
//		Sfx.playSnd(baos.toByteArray());
		SoundSystem.SoundBackGround.playMusic("sound/Ken stage.mp3");
		
	}
    public static class Sfx {
    	public static void playSnd(final byte[] sound) {
    		playSnd(sound, true);
    	}
		public static void playSnd(final byte[] sound, final AtomicBoolean isPlaying) {
			Runnable r = new Runnable() {
				public void run() {
					try {
						if (sound != null)
							playAudio(sound);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						isPlaying.set(false);
					}
				}
			};
			new Thread(r).start();
			
		}
    	public static void playSnd(final byte[] sound, boolean async) {
    		if (async) {
    			Runnable r = new Runnable() {
					public void run() {
						try {
							playAudio(sound);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
    			};
    			new Thread(r).start();
    		} else {
    			try {
    				playAudio(sound);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}

        private static void playAudio(byte[] data) throws UnsupportedAudioFileException, IOException {
        	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info( SourceDataLine.class, audioFormat );
            if ( !AudioSystem.isLineSupported( info ) ) {
            	throw new IllegalArgumentException("Sound Type not supported");
            }
       
            try {
               SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine( info );
               dataLine.open( audioFormat );
               if( dataLine.isControlSupported( FloatControl.Type.MASTER_GAIN ) ) {
                  FloatControl volume = (FloatControl) dataLine.getControl( FloatControl.Type.MASTER_GAIN );
                  volume.setValue(-5);
               }
               dataLine.start();
         
               int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
               byte [] buffer = new byte[ bufferSize ];
         
               try {
                  int bytesRead = 0;
                  while ( bytesRead >= 0 ) {
                     bytesRead = audioInputStream.read( buffer, 0, buffer.length );
                     if ( bytesRead >= 0 ) {
                        int framesWritten = dataLine.write( buffer, 0, bytesRead );
                     }
                  }
               } catch ( IOException e ) {
            	   throw new IllegalArgumentException("IOException");
               }
               dataLine.drain();
               dataLine.close();
            } catch ( LineUnavailableException e ) {
               e.printStackTrace();
            }
         }


    }
}
