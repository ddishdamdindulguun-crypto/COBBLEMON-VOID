{
	name: "Battle Hungry",
	num: 9106,
	rating: 3,
	flags: {},
	onAnyAfterMove(source, target, move) {
		const holder = this.effectState.target;
		if (!holder || holder.fainted) return;
		if (!source || source.isAlly(holder) || source === holder) return;
		if (move.category !== 'Status') return;
		source.addVolatile('taunt', holder);
		source.trySetStatus('brn', holder);
	},
}
