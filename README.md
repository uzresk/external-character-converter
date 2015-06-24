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

### cleate executable jar

`mvn clean package`

### Exec SjisConverter

`java -Dpath=convertRule.xml -jar external-character-converter-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Licence

MIT



