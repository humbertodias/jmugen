package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Trans extends StateCtrlFunction {
/*
Required parameters:
  trans = trans_type (string)
    trans_type must be one of the following:
      default  - does nothing
      none     - disables transparency
      add      - draws with full additive transparency
      addalpha - draws with additive transparency (alpha must be specified)
      add1     - draws with additive transparency, with alpha at 256,128
      sub      - draws with full subtractive transparency
 
Optional parameters:
  alpha = source_alpha, dest_alpha (int, int)
    These are the source and destination alpha values for the addalpha
    trans type. Valid values are from 0 (low) to 256 (high). If omitted,
    defaults to 256,0.
 
Example:
  ; Fades the character in, over 256 ticks.
  type = Trans
  trigger1 = time < 256
  trans = add_alpha
  alpha = time, 256-time 
 */
    // TODO : trans
    public Trans() {
        super("trans", new String[] {"trans", "alpha"});
    }
    
    
    @Override
    public Valueable[] parse(String name, String value) {
        return null;
    }
    public void addParam(String name, Valueable[] param) {

    }
}
