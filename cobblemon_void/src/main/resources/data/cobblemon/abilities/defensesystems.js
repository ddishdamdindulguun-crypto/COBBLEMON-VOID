{
	name: "Defense Systems",
	num: 9102,
	rating: 3.5,
	flags: {breakable: 1},
	onTryHit(target, source, move) {
		if (target !== source && move.type === 'Cosmic') {
			if (!this.boost({spe: 1})) {
				this.add('-immune', target, '[from] ability: Defense Systems');
			}
			return null;
		}
	},
}
