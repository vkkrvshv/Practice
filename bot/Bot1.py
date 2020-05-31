import telebot
import requests
import pyowm


api_url = "http://api.openweathermap.org/data/2.5/weather"
bot = telebot.TeleBot('1224340196:AAGH85S3FFbbUX688Adsa_iP2kT4QrQF-CM')
telebot.apihelper.proxy = {'https': 'socks5://geek:socks@t.geekclass.ru:7777'}
keyboard = telebot.types.InlineKeyboardMarkup()
keyweath = telebot.types.InlineKeyboardButton(text='Weather', callback_data='Weather')
keyboard.add(keyweath)
keyhelp = telebot.types.InlineKeyboardButton(text='Help', callback_data='Help')
keyboard.add(keyhelp)


@bot.message_handler(commands=['start'])
def start_message(message):
    bot.send_message(message.chat.id, 'Hi there! I have recieved "/start". How can i help u?', reply_markup=keyboard)

@bot.message_handler(content_types=['text'])
def send_text(message):
	if message.text.lower() == 'hi' or 'hello':
		bot.send_message(message.chat.id, 'Hello, my dear!')
	else:
		bot.send_message(message.chat.id, 'I do not understand you! Try again.')


@bot.callback_query_handler(func=lambda call: True)
def callback_worker(call):
	if call.data == 'Weather':
		bot.send_message(call.message.chat.id, 'Write the name of the city.')
	@bot.message_handler(content_types=['text'])
	def send_text(message):
		city = message.text
		params = {
			'q': city,
			'appid': '09f3770cd08d73eb2ed8f9fd8ba01f12',
			'units': 'metric',
			'lang': 'eng'
		}
		template = "{}\nThe temperature: {}°C,\nBut it feels more like {}°C\n" \
				   "Humidity: {}%\nWind speed: {} m/sec\nCloudiness: {}%\n\n"
		res = requests.get(api_url, params=params)
		data = res.json()
		bot.send_message(message.from_user.id,
						 template.format(data["name"], data["main"]["temp"], data["main"]["feels_like"],
										 data["main"]["humidity"], data["wind"]["speed"],
										 data["clouds"]["all"]))
	if call.data == 'Help':
		bot.send_message(call.message.chat.id, 'Please, write the name of the city in such a format: \nEnglish: Moscow\nRussian: Москва')
bot.polling(none_stop=True)
