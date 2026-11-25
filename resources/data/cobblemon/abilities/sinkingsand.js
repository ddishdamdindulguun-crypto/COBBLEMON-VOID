{
	name: "Sinking Sand",
	num: 9105,
	rating: 2,
	flags: {},
	onFractionalPriority: -0.1,
	onBasePowerPriority: 21,
	onBasePower(basePower, attacker, defender, move) {
		if (!defender || move.category === 'Status') return;
		const spe = defender.getStat('spe', false, true);
		const bonus = 1 + 0.02 * Math.floor(spe / 10);
		return this.chainModify(bonus);
	},
}
