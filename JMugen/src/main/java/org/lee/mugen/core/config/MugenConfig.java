//package org.lee.mugen.core.config;
//
//public class MugenConfig {
//	private Option option = new Option();
//	public static class Option {
//		private int difficulty;
//		private int life;
//		private long time;
//		private int gamespeed;
//		private int wavvolume;
//		private int midivolume;
//		public int getDifficulty() {
//			return difficulty;
//		}
//		public void setDifficulty(int difficulty) {
//			this.difficulty = difficulty;
//		}
//		public int getGamespeed() {
//			return gamespeed;
//		}
//		public void setGamespeed(int gamespeed) {
//			this.gamespeed = gamespeed;
//		}
//		public int getLife() {
//			return life;
//		}
//		public void setLife(int life) {
//			this.life = life;
//		}
//		public int getMidivolume() {
//			return midivolume;
//		}
//		public void setMidivolume(int midivolume) {
//			this.midivolume = midivolume;
//		}
//		public long getTime() {
//			return time;
//		}
//		public void setTime(long time) {
//			this.time = time;
//		}
//		public int getWavvolume() {
//			return wavvolume;
//		}
//		public void setWavvolume(int wavvolume) {
//			this.wavvolume = wavvolume;
//		}
//		
//		
//	}
//	
//	
//	// Team-only config
//	private Team team = new Team();
//	public static class Team {
//		private int oneVsTwoLive;
//		private boolean loseonko;
//		// Not accessible in options screen
//		private String motif;
//
//		public boolean isLoseonko() {
//			return loseonko;
//		}
//		public void setLoseonko(boolean loseonko) {
//			this.loseonko = loseonko;
//		}
//		public void set1vs2life(int value) {
//			oneVsTwoLive = value;
//		}
//		public int get1vs2Live() {
//			return oneVsTwoLive;
//		}
//		public String getMotif() {
//			return motif;
//		}
//		private void setMotif(String motif) {
//			this.motif = motif;
//		}
//	}
//	
//
//	private Rules rules = new Rules();
//	public static class Rules {
//		//Keep this set at VS. It's the only option supported for now.
//		public static enum GameType {
//			VS
//		}
//		private GameType gameType;
//		public GameType getGameType() {
//			return gameType;
//		}
//		public void setGameType(GameType gameType) {
//			this.gameType = gameType;
//		}
//		
//	}
//	
//	private Default _default;
//	public static class Default {
//		private Attack attack = new Attack();
//		private Attack gethit = new Attack();
//		public static class Attack {
//			private float lifetopowermul;
//
//			public float getLifetopowermul() {
//				return lifetopowermul;
//			}
//
//			public void setLifetopowermul(float lifetopowermul) {
//				this.lifetopowermul = lifetopowermul;
//			}
//			
//		}
//		public Attack getAttack() {
//			return attack;
//		}
//		
//	}
//	
//	
//	private Super _super = new Super();
//	public static class Super {
//		private float targetdefencemul;
//
//		public float getTargetdefencemul() {
//			return targetdefencemul;
//		}
//
//		public void setTargetdefencemul(float targetdefencemul) {
//			this.targetdefencemul = targetdefencemul;
//		}
//		
//	}
//
//	private Config config = new Config();
//	public static class Config {
//		private int gamespeed;
//		private int drawshadows;
//		private int afterimagemax;
//		private int layeredspritemax;
//		private int explodmax;
//		private int sysexplodmax;
//		private int helpermax;
//		private int playerprojectilemax;
//		private int firstrun;
//		public int getAfterimagemax() {
//			return afterimagemax;
//		}
//		public void setAfterimagemax(int afterimagemax) {
//			this.afterimagemax = afterimagemax;
//		}
//		public int getDrawshadows() {
//			return drawshadows;
//		}
//		public void setDrawshadows(int drawshadows) {
//			this.drawshadows = drawshadows;
//		}
//		public int getExplodmax() {
//			return explodmax;
//		}
//		public void setExplodmax(int explodmax) {
//			this.explodmax = explodmax;
//		}
//		public int getFirstrun() {
//			return firstrun;
//		}
//		public void setFirstrun(int firstrun) {
//			this.firstrun = firstrun;
//		}
//		public int getGamespeed() {
//			return gamespeed;
//		}
//		public void setGamespeed(int gamespeed) {
//			this.gamespeed = gamespeed;
//		}
//		public int getHelpermax() {
//			return helpermax;
//		}
//		public void setHelpermax(int helpermax) {
//			this.helpermax = helpermax;
//		}
//		public int getLayeredspritemax() {
//			return layeredspritemax;
//		}
//		public void setLayeredspritemax(int layeredspritemax) {
//			this.layeredspritemax = layeredspritemax;
//		}
//		public int getPlayerprojectilemax() {
//			return playerprojectilemax;
//		}
//		public void setPlayerprojectilemax(int playerprojectilemax) {
//			this.playerprojectilemax = playerprojectilemax;
//		}
//		public int getSysexplodmax() {
//			return sysexplodmax;
//		}
//		public void setSysexplodmax(int sysexplodmax) {
//			this.sysexplodmax = sysexplodmax;
//		}
//		
//		
//		
//	}
//	private Debug debug = new Debug();
//	public static class Debug {
//		private int debug;
//		private int allowdebugmode;
//		private int allowdebugkeys;
//		private int speedup;
//		private String startstage;
//		public int getAllowdebugkeys() {
//			return allowdebugkeys;
//		}
//		public void setAllowdebugkeys(int allowdebugkeys) {
//			this.allowdebugkeys = allowdebugkeys;
//		}
//		public int getAllowdebugmode() {
//			return allowdebugmode;
//		}
//		public void setAllowdebugmode(int allowdebugmode) {
//			this.allowdebugmode = allowdebugmode;
//		}
//		public int getDebug() {
//			return debug;
//		}
//		public void setDebug(int debug) {
//			this.debug = debug;
//		}
//		public int getSpeedup() {
//			return speedup;
//		}
//		public void setSpeedup(int speedup) {
//			this.speedup = speedup;
//		}
//		public String getStartstage() {
//			return startstage;
//		}
//		public void setStartstage(String startstage) {
//			this.startstage = startstage;
//		}
//		
//	}
//
//	[Video Win]
//	Width  = 320
//	Height = 240
//	Depth = 16
//	Stretch = 0
//
//	 /** Set this parameter to use a resolution-doubling filter. You will
//	  *  need a fast machine to use these filters. You will need to increase
//	  *  the screen resolution to at least 640x480 for these modes.
//	  *  0 - off
//	  *  1 - diagonal edge detection
//	  *  2 - bilinear filtering
//	  *  3 - horizontal scanlines
//	  */	
//	DoubleRes = 0
//	DXmode = Windowed
//	VRetrace = 0
//	BlitMode = PageFlip
//
//	[Video Linux]
//	Width  = 320
//	Height = 240
//	Depth = 16
//	Stretch = 1
//	DoubleRes = 0
//	VRetrace = 0
//	FullScreen = 0
//	BlitMode = PageFlip
//
//	[Sound Win]
//	Sound = 1
//	StereoEffects = 1
//	PanningWidth = 240
//	ReverseStereo = 0
//	WavDevice = Auto
//	WavChannels = 12
//	ModVoices = 6
//	MidiDevice = Auto
//	MasterWavVolume = 255
//
//	WavVolume = 128
//	MidiVolume = 192
//	MP3Volume = 135
//	ModVolume = 80
//	CDAVolume = -1
//
//	PlayMIDI = 1
//	PlayMP3 = 1
//	PlayMOD = 1
//	PlayCDA = 1
//
//	// Leave blank to use your default CD-ROM drive.
//	CDADevice =
//	PauseBGMOnDefocus = 1
//
//	// Not need
//	//plugin = plugins/in_mp3.dll, mp3, mp2, mpg
//	//plugin = plugins/in_vorbis.dll, ogg
//	//plugin = plugins/in_adx.dll, adx
//
//	[Sound Linux]
//	Sound = 1
//	StereoEffects = 1
//	PanningWidth = 240
//	ReverseStereo = 0
//	WavDevice = Auto
//	WavChannels = 12
//	ModVoices = 6
//
//	MidiDevice = Auto
//	MasterWavVolume = 255
//
//	WavVolume = 128
//	MidiVolume = 128
//	MP3Volume = 135
//	ModVolume = 80
//	CDAVolume = -1
//
//	PlayMIDI = 1
//	PlayMP3 = 1
//	PlayMOD = 1
//	PlayCDA = 1
//
//	CDADevice =
//
//
//	[Misc]
//	  //Number of extra players to cache in memory.
//	  //Set to a lower number to decrease memory usage, at cost of
//	  //more frequent loading.
//	PlayerCache = 1
//
//	//Set to 1 to allow precaching. Precaching attempts to start loading
//	//player data as early as possible, to reduce apparent loading times
//	//between matches. To get the best performance, set PlayerCache to at
//	//least 1. The optimal number for PlayerCache is 4 when precaching is
//	//enabled. Precaching is not available in DOS.
//	Precache = 1
//
//	//Set to 1 to enable large-buffer reads of sprite and sound data.
//	//Set to 0 (off) to decrease memory usage, at cost of slower
//	//loading.
//	BufferedRead = 1
//
//	//;Set to 1 to free system.def data from memory whenever possible.
//	//;This decreases memory usage, in exchange for loading time
//	//;before system screens.
//	UnloadSystem = 1
//
//	//;Set to 1 to pause MUGEN when the MUGEN window loses focus (will also
//	//;pause BGM regardless of the PauseBGMOnDefocus setting).
//	//;Leave at 0 to let MUGEN run in the background.
//	//;Regardless of this setting, MUGEN will always pause on defocus if 
//	//;it is running fullscreen.
//	PauseOnDefocus = 1
//
//
//	[Arcade]
//	 //;Set to 0 for computer to choose color 1 if possible.
//	 //;Set to 1 for computer to randomly choose a color.
//	AI.RandomColor = 1
//
//	//;This option allows the AI to input commands without
//	//;having to actually press any keys (in effect, cheating)
//	//;Set to 1 to enable, 0 to disable. Enabling this option
//	//;can give the illusion of a smarter AI.
//	AI.Cheat = 1
//
//	//;Arcade Mode AI ramping. For both parameters below, the first number
//	//;corresponds to the number of matches won, and the second number to the
//	//;AI difficulty offset. The actual difficulty is the sum of the AI
//	//;difficulty level (set in the options menu) and the value of the offset
//	//;at a particular match.
//	//;  AIramp.start = start_match, start_diff
//	//;  AIramp.end   = end_match, end_diff
//	//;The difficulty offset function is a constant value of start_diff from
//	//;the first match until start_match matches have been won. From then the
//	//;offset value increases linearly from start_diff to end_diff. After
//	//;end_diff matches have been won, the offset value is end_diff.
//	//;  e_d            /----------
//	//;               /
//	//;  s_d _______/
//	//;     ^      ^     ^        ^
//	//;   1st_m   s_m   e_m     last_m
//	//;For example, if you have:
//	//;  AIramp.start = 2,0
//	//;  AIramp.end   = 4,2
//	//;For 6 matches at level 4, the difficulty will be (by match):
//	//;  4,4,4,5,6,6
//	arcade.AIramp.start = 2, 0
//	arcade.AIramp.end   = 4, 2
//
//	//;Team Mode AI ramping
//	team.AIramp.start = 1, 0
//	team.AIramp.end   = 3, 2
//
//	//;Survival Mode AI ramping
//	survival.AIramp.start = 0, -3
//	survival.AIramp.end   = 16, 4
//
//
//	[Input]
//	P1.UseKeyboard = 1
//	P2.UseKeyboard = 1
//	P1.Joystick.type = 0
//	P2.Joystick.type = 0
//	ForceFeedback = 0    ;True to enable force feedback (DOS only)
//
//	[P1 Keys]
//	Jump   = 200
//	Crouch = 208
//	Left   = 203
//	Right  = 205
//	A      = 16
//	B      = 17
//	C      = 18
//	X      = 30
//	Y      = 31
//	Z      = 32
//	Start  = 28
//
//	[P2 Keys]
//	Jump   = 72
//	Crouch = 80
//	Left   = 75
//	Right  = 77
//	A      = 74
//	B      = 78
//	C      = 156
//	X      = 69
//	Y      = 181
//	Z      = 55
//	Start  = 81
//
//	[P1 Joystick]
//	Jump   = 1
//	Crouch = 2
//	Left   = 3
//	Right  = 4
//	A      = 5
//	B      = 6
//	C      = 9
//	X      = 7
//	Y      = 8
//	Z      = 10
//	Start  = 13
//
//	[P2 Joystick]
//	Jump   = 1
//	Crouch = 2
//	Left   = 3
//	Right  = 4
//	A      = 5
//	B      = 6
//	C      = 9
//	X      = 7
//	Y      = 8
//	Z      = 10
//	Start  = 13
//
//	[P1 Joystick Win]
//	Jump   = 1
//	Crouch = 2
//	Left   = 3
//	Right  = 4
//	A      = 7
//	B      = 8
//	C      = 10
//	X      = 6
//	Y      = 5
//	Z      = 9
//	Start  = 14
//
//	[P2 Joystick Win]
//	Jump   = 33
//	Crouch = 34
//	Left   = 35
//	Right  = 36
//	A      = 39
//	B      = 40
//	C      = 0
//	X      = 37
//	Y      = 38
//	Z      = 0
//	Start  = 0
//
//}
