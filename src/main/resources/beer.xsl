<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:key name="beerByType" match="beer" use="type" />

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/Beer">
        <TransformedBeer>
            <xsl:for-each select="beer[generate-id() = generate-id(key('beerByType', type)[1])]">
                <BeerGroup>
                    <Type><xsl:value-of select="type"/></Type>
                    <Beers>
                        <xsl:for-each select="key('beerByType', type)">
                            <Beer>
                                <Id><xsl:value-of select="id"/></Id>
                                <Name><xsl:value-of select="name"/></Name>
                                <Al><xsl:value-of select="al"/></Al>
                                <Manufacturer><xsl:value-of select="manufacturer"/></Manufacturer>
                                <Ingredients>
                                    <xsl:for-each select="ingredients/ingredient">
                                        <Ingredient><xsl:value-of select="."/></Ingredient>
                                    </xsl:for-each>
                                </Ingredients>
                                <Chars>
                                    <Transparency><xsl:value-of select="chars/transparency"/></Transparency>
                                    <Filtered><xsl:value-of select="chars/filtered"/></Filtered>
                                    <NutritionalValue><xsl:value-of select="chars/nutritionalValue"/></NutritionalValue>
                                    <BottlingMethod>
                                        <Volume><xsl:value-of select="chars/bottlingMethod/volume"/></Volume>
                                        <Material><xsl:value-of select="chars/bottlingMethod/material"/></Material>
                                    </BottlingMethod>
                                </Chars>
                            </Beer>
                        </xsl:for-each>
                    </Beers>
                </BeerGroup>
            </xsl:for-each>
        </TransformedBeer>
    </xsl:template>
</xsl:stylesheet>