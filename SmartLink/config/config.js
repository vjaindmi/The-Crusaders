
var config = 
{
	address: "0.0.0.0",
	electronOptions: {
		
	},
	ipWhitelist: [
		
	],
	modules: [
		{
			module: "alert",
			config: {
				
			}
		},
		{
			module: "clock",
			position: "top_left",
			config: {
				
			}
		},
		{
			module: "compliments",
			position: "lower_third",
			header: "",
			config: {
				compliments: {
					anytime: [
						"Hey there!"
					],
					morning: [
						"Good morning, handsome!",
						"Enjoy your day!",
						"How was your sleep?"
					],
					afternoon: [
						"Hello, beauty!",
						"You look sexy!",
						"Looking good today!"
					],
					evening: [
						"Wow, you look hot!",
						"You look nice!",
						"Hi, sexy!"
					]
				}
			}
		},
		{
			module: "currentweather",
			position: "top_right",
			config: {
				location: "Delhi",
				locationID: "1273294",
				appid: "dcc7f95f6830e5aa835abc6868790b40",
				iconTable: {
					"01d": "wi-day-sunny",
					"02d": "wi-day-cloudy",
					"03d": "wi-cloudy",
					"04d": "wi-cloudy-windy",
					"09d": "wi-showers",
					"10d": "wi-rain",
					"11d": "wi-thunderstorm",
					"13d": "wi-snow",
					"50d": "wi-fog",
					"01n": "wi-night-clear",
					"02n": "wi-night-cloudy",
					"03n": "wi-night-cloudy",
					"04n": "wi-night-cloudy",
					"09n": "wi-night-showers",
					"10n": "wi-night-rain",
					"11n": "wi-night-thunderstorm",
					"13n": "wi-night-snow",
					"50n": "wi-night-alt-cloudy-windy"
				}
			}
		},
		{
			module: "newsfeed",
			position: "bottom_bar",
			config: {
				feeds: [
					{
						title: "The Times of India",
						url: "https://timesofindia.indiatimes.com/rssfeedstopstories.cms"
					}
				],
				startTags: [
					
				],
				endTags: [
					
				],
				prohibitedWords: [
					
				]
			}
		},
		{
			module: "MMM-Remote-Control",
			position: "bottom_left",
			config: {
				
			}
		}
	],
	paths: {
		modules: "modules",
		vendor: "vendor"
	}
}

if (typeof module !== 'undefined') {module.exports = config;}
