package org.lee.mugen.parser.air;

import java.io.Serializable;
import java.util.ArrayList;

public class AirGroup implements Serializable {
    public int action;
    public int loopStart = -1;
    public ArrayList<AirData> airDataList = new ArrayList<AirData>();
}