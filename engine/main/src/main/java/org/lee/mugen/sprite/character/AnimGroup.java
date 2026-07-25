package org.lee.mugen.sprite.character;

import java.io.Serializable;
import java.util.ArrayList;

import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirGroup;

public class AnimGroup implements Serializable {
        private int _groupID;
        private int _imgLoopStart;
        private AnimElement[] _ImgSprArray;

        public int getGroupId() {
            return _groupID;
        }
        public int getImgLoopStart() {
            return _imgLoopStart;
        }
        public AnimElement[] getImgSprites() {
            return _ImgSprArray;
        }
        public AnimGroup(AirGroup airGrp) {
            _groupID = airGrp.action;
            _imgLoopStart = airGrp.loopStart;

            ArrayList<AnimElement> ImgSprList = new ArrayList<AnimElement>();
            for (AirData airData: airGrp.airDataList) {
                AnimElement imgSprInfo = new AnimElement(airData);
                ImgSprList.add(imgSprInfo);
            }
            _ImgSprArray = ImgSprList.toArray(new AnimElement[0]);

        }
}
