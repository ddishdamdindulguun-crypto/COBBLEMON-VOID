{
	name: "Aurora Aura",
	num: 9103,
	rating: 2.5,
	flags: {breakable: 1},
	onSourceModifyDamage(damage, source, target, move) {
		if (this.field.isWeather(['hail', 'snow', 'snowscape'])) {
			return this.chainModify(0.5);
		}
		return this.chainModify(2);
	},
}
