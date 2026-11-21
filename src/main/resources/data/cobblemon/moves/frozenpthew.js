{
	num: 9002,
	accuracy: 100,
	basePower: 20,
	category: "Special",
	name: "Frozen Pthew",
	pp: 10,
	priority: 0,
	flags: {protect: 1, mirror: 1, metronome: 1},
	onModifyMove(move, pokemon) {
		const alive = pokemon.side.pokemon.filter(p => p && !p.fainted).length;
		move.multihit = Math.max(1, alive);
	},
	multihitType: 'beatup',
	secondary: null,
	target: "normal",
	type: "Ice",
	contestType: "Cool",
}
