{
	num: 9004,
	accuracy: 100,
	basePower: 75,
	basePowerCallback(pokemon, target, move) {
		if (target.status === 'psn' || target.status === 'tox') {
			this.debug('Blight Hammer double power');
			return move.basePower * 2;
		}
		return move.basePower;
	},
	category: "Physical",
	name: "Blight Hammer",
	pp: 10,
	priority: 0,
	flags: {contact: 1, protect: 1, mirror: 1, metronome: 1},
	secondary: {
		chance: 25,
		status: 'psn',
	},
	target: "normal",
	type: "Poison",
	contestType: "Tough",
}
