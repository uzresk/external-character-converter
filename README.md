# external-character-converter

It will convert the external character in accordance with the settings file

## Requires

1. Java1.6 later
2. maven

## Usage

### Get the code
`$ git clone https://github.com/uzresk/external-character-converter.git`

### convertRule.xml

	<?xml version="1.0" encoding="UTF-8"?>
	<convertRule>
		<input file="/path/file.txt" charasetName="MS932"/>
		<output file="/path/file.txt" charasetName="MS932"/>
		<mappings>
			<mapping src="0xf88c" dest="0x9ad9"/>
		</mappings>
	</convertRule>

### exec SjisConverter

## Licence 

MIT

## Reference

http://blue-red.ddo.jp/~ao/wiki/wiki.cgi?page=Java+%A4%CE+MS932%2C+Cp943C%2C+SJIS+%A4%CE%B0%E3%A4%A4
