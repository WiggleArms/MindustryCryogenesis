uniform sampler2D u_texture;
uniform float u_time;

varying vec2 v_texCoords;

void main(){
	vec2 uv = v_texCoords;

	// gentle flow
	uv.x += sin(uv.y * 8.0 + u_time) * 0.01;

	// bubbling distortion
	uv.y += cos(uv.x * 12.0 + u_time * 1.5) * 0.01;

	vec4 color = texture2D(u_texture, uv);

	// brighten highlights
	float shimmer = sin((uv.x + uv.y + u_time) * 20.0) * 0.05;
	color.rgb += shimmer;

	gl_FragColor = color;
}