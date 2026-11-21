{
	name: "Leeching Companions",
	num: 9108,
	rating: 3,
	flags: {},
	onDamagePriority: 1,
	onDamage(damage, target, source, effect) {
		if (effect.id === 'psn' || effect.id === 'tox') {
			this.heal(target.baseMaxhp / 8);
			return false;
		}
	},
}
