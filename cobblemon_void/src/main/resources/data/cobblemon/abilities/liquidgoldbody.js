{
	name: "Liquid Gold Body",
	num: 9101,
	rating: 3.5,
	flags: {breakable: 1},
	onTryHit(target, source, move) {
		if (target !== source && move.type === 'Fire') {
			if (!this.boost({spe: 1})) {
				this.add('-immune', target, '[from] ability: Liquid Gold Body');
			}
			return null;
		}
	},
	onModifySpe(spe, pokemon) {
		if (pokemon.status === 'brn') {
			return this.chainModify(1.5);
		}
	},
}
