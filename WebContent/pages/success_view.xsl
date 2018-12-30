<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" doctype-system="about:legacy-compat" encoding="UTF-8" indent="yes"></xsl:output>
	<xsl:template match="/"><!-- / 代表xml文档的根节点 -->
		
		<html>
			<header>
				<title><xsl:value-of select="view/header/title"></xsl:value-of></title>
			</header>
			
			<body>
				<form>
					<xsl:attribute name="name">
						<xsl:value-of select="view/body/form/name"></xsl:value-of>
					</xsl:attribute>
					<xsl:attribute name="action">
						<xsl:value-of select="view/body/form/action"></xsl:value-of>
					</xsl:attribute>
					<xsl:attribute name="method">
						<xsl:value-of select="view/body/form/method"></xsl:value-of>
					</xsl:attribute>
					
					<xsl:for-each select="view/body/form/textView">
						<input type="text">
							<xsl:attribute name="name">
								<xsl:value-of select="./name"></xsl:value-of><!-- ./指在 view/body/form/textView下-->
							</xsl:attribute>
							<xsl:attribute name="label">
								<xsl:value-of select="./label"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="value">
								<xsl:value-of select="./value"></xsl:value-of>
							</xsl:attribute>
						</input>
						<br/>
					</xsl:for-each>
			
					<input type="submit">
						<xsl:attribute name="name">
							<xsl:value-of select="view/body/form/buttonView/name"></xsl:value-of>
						</xsl:attribute>
						<xsl:attribute name="label">
							<xsl:value-of select="view/body/form/buttonView/label"></xsl:value-of>
						</xsl:attribute>
					</input>
				</form>
				
			</body>
		</html>
		
		
		
		
	</xsl:template>
</xsl:stylesheet>