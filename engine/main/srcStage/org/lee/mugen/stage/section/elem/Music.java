package org.lee.mugen.stage.section.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.stage.Stage;
import org.lee.mugen.util.MugenTools;

public class Music implements Section {
	private Stage parent = null;
	public Music(Stage stage) {
		parent = stage;
	}
	 //Put a filename for a MOD, MP3 or MIDI here, or just leave it
	 //blank if you don't want music. If an invalid filename is
	 //given, then no music will play. To play CD audio, put
	 //the track number followed by ".da". Using a track number of
	 //0 will play a random audio track. For example, to play
	 //track 3 from a music CD, use:
	 //  bgmusic = 3.da
	private String bgmusic = ""; 

	 //Adjust the volume. 0 is normal, negative for softer, and
	 //positive for louder (only for mp3, mods and CDA)
	private int bgvolume = 0;

	public String getBgmusic() {
		return bgmusic;
	}

	public void setBgmusic(String bgmusic) {
		this.bgmusic = bgmusic;
	}

	public int getBgvolume() {
		return bgvolume;
	}

	public void setBgvolume(int bgvolume) {
		this.bgvolume = bgvolume;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("bgmusic")) {
			this.bgmusic = value;
		} else if (name.equals("bgvolume")) {
			if (!MugenTools.isEmpty(value))
				bgvolume = Integer.parseInt(value);
		}
		
	}
}
