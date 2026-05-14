#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
uniform vec4 add;
uniform vec4 mul;
uniform vec4 ampl;
uniform float alpha;

varying vec4 v_color;
varying vec2 v_texCoords;

void main() {
    vec4 sprColor = texture2D(u_texture, v_texCoords);
    vec3 adjustedRGB = (sprColor.rgb + add.rgb + ampl.rgb) * mul.rgb;
    adjustedRGB = clamp(adjustedRGB, 0.0, 1.0);
    float finalAlpha = sprColor.a * alpha;
    vec4 outColor = vec4(adjustedRGB, finalAlpha) * v_color;
    gl_FragColor = outColor;
}
