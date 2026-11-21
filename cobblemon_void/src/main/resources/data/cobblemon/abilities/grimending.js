{
	name: "Grim Ending",
	num: 9107,
	rating: 4,
	flags: {},
	onStart(pokemon) {
		const options = [
			['RainDance', 'Water'],
			['SunnyDay', 'Fire'],
			['Sandstorm', 'Rock'],
			['Snowscape', 'Ice'],
		];
		const pick = this.sample(options);
		this.field.setWeather(pick[0].toLowerCase(), pokemon);
		pokemon.setType(['Ghost', pick[1]]);
		this.add('-start', pokemon, 'typechange', 'Ghost/' + pick[1], '[from] ability: Grim Ending');
	},
}
