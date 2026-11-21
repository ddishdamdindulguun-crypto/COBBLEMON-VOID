{
	num: 9001,
	accuracy: 100,
	basePower: 80,
	category: "Special",
	name: "Silent Depths",
	pp: 10,
	priority: 0,
	flags: {protect: 1, mirror: 1, metronome: 1},
	secondary: {
		chance: 50,
		onHit(target, source) {
			target.addVolatile('disable', source);
		},
	},
	target: "normal",
	type: "Water",
	contestType: "Beautiful",
}
