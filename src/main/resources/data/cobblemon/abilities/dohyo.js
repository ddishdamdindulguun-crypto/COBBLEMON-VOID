{
	name: "Dohyo",
	num: 9104,
	rating: 3,
	flags: {},
	onBasePowerPriority: 23,
	onBasePower(basePower, attacker, defender, move) {
		if (['circlethrow', 'seismictoss', 'vitalthrow'].includes(move.id)) {
			return this.chainModify(1.5);
		}
	},
}
