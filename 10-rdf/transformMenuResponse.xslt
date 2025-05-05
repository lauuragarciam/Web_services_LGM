<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  exclude-result-prefixes="xsl">

  <xsl:output method="text" encoding="UTF-8"/>

  <xsl:template match="*[local-name()='getMenuResponse']">
    <xsl:text>@prefix schema: &lt;http://schema.org/&gt; .&#10;</xsl:text>
    <xsl:text>@prefix ex: &lt;https://example.org/restaurant/&gt; .&#10;</xsl:text>
    <xsl:text>@prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .&#10;&#10;</xsl:text>

    <xsl:for-each select="*[local-name()='return']">
      <xsl:variable name="parts" select="tokenize(., ' - ')" />
      <xsl:text>ex:item</xsl:text><xsl:number value="position()"/><xsl:text> a schema:MenuItem ;&#10;</xsl:text>
      <xsl:text>  schema:name "</xsl:text><xsl:value-of select="$parts[1]"/><xsl:text>" ;&#10;</xsl:text>
      <xsl:text>  schema:price "</xsl:text>
      <xsl:value-of select="replace($parts[2], '\$', '')"/>
      <xsl:text>"^^xsd:decimal .&#10;&#10;</xsl:text>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="text()"/>
</xsl:stylesheet>
