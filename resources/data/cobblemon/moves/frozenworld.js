{
	num: 9003,
	accuracy: true,
	basePower: 0,
	category: "Status",
	name: "Frozen World",
	pp: 5,
	priority: 0,
	flags: {metronome: 1},
	pseudoWeather: 'frozenworld',
	condition: {
		duration: 5,
		onFieldStart(field, source) {
			this.add('-fieldstart', 'move: Frozen World', '[of] ' + source);
			this.field.setWeather('snowscape', source);
		},
		onFieldResidualOrder: 1,
		onFieldResidual() {
			for (const pokemon of this.getAllActive()) {
				if (pokemon.hasType('Ice') || pokemon.fainted) continue;
				pokemon.m.frozenWorldHits = (pokemon.m.frozenWorldHits || 0) + 1;
				this.damage(pokemon.baseMaxhp / 16, pokemon);
				if (pokemon.m.frozenWorldHits >= 3) {
					pokemon.m.frozenWorldHits = 0;
					pokemon.trySetStatus('frz', pokemon);
				}
			}
		},
		onFieldEnd() {
			this.add('-fieldend', 'move: Frozen World');
		},
	},
	secondary: null,
	target: "all",
	type: "Ice",
	contestType: "Beautiful",
}
