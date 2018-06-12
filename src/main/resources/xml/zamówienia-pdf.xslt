<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:msxsl="urn:schemas-microsoft-com:xslt"
                version="2.0"
                exclude-result-prefixes="msxsl">
    <xsl:output method="xml"
                encoding="utf-8"/>


    <xsl:template match="/">
        <fo:root language="PL" font-family="Calibri">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="Raport" page-height="297mm" page-width="210mm" margin-top="5mm"
                                       margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
                    <fo:region-body margin-top="10mm" margin-bottom="10mm"/>
                    <fo:region-after region-name="xsl-region-after" extent="20mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="Raport">
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block>
                        <xsl:apply-templates select="/sklep/data"/>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-weight="bold" text-align="center" font-size="large">
                        <xsl:text>Liczba produktów w kategorii</xsl:text>
                    </fo:block>
                    <xsl:apply-templates select="/sklep/kategorie/kategoria"/>
                    <fo:block font-weight="bold" text-align="center" font-size="large" margin-bottom="10mm"
                              margin-top="10mm" line-height="20mm">
                        <xsl:text>Zamówienia</xsl:text>
                    </fo:block>
                    <fo:table margin-left="2mm" margin-right="2mm" margin-bottom="2mm" margin-top="2mm"
                              table-layout="fixed" width="200mm" font-size="8px">
                        <fo:table-column column-width="proportional-column-width(2)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(2)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(2)"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Klient</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Produkt</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Kategoria</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Cena</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Suma częściowa</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Suma VAT</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Suma końcowa</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Suma kwot VAT</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Data</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Opis</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <xsl:apply-templates select="/sklep/zamówienia/zamówienie"/>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="/sklep/zamówienia/zamówienie">
        <xsl:for-each select="produkt">
            <fo:table-row line-height="20mm" top="2mm" bottom="2mm" left="2mm" right="2mm" border-color="black"
                          border-width="0.6mm" border-style="solid">
                <xsl:if test="position() = 1">
                    <fo:table-cell number-rows-spanned="{count(../produkt)}">
                        <fo:block line-height="5mm">
                            <xsl:value-of select="../klient"/>
                        </fo:block>
                    </fo:table-cell>
                </xsl:if>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="id"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="kategoria"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="cena"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="suma_częściowa"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="suma_kwot_vat"/>
                    </fo:block>
                </fo:table-cell>
                <xsl:if test="position() = 1">
                    <fo:table-cell number-rows-spanned="{count(../produkt)}">
                        <fo:block line-height="5mm">
                            <xsl:value-of select="../suma_końcowa"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell number-rows-spanned="{count(../produkt)}">
                        <fo:block line-height="5mm">
                            <xsl:value-of select="../suma_kwot_vat"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell number-rows-spanned="{count(../produkt)}">
                        <fo:block line-height="5mm">
                            <xsl:value-of select="../data"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell number-rows-spanned="{count(../produkt)}">
                        <fo:block line-height="5mm">
                            <xsl:value-of select="../opis"/>
                        </fo:block>
                    </fo:table-cell>
                </xsl:if>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="/sklep/kategorie/kategoria">
        <fo:block border-color="black" border-width="0.6mm" border-style="solid" margin-top="5mm" margin-bottom="5mm"
                  margin-left="5mm" margin-right="5mm">
            <fo:list-block>
                <fo:list-item>
                    <fo:list-item-label>
                        <fo:block>Nazwa:</fo:block>
                        <fo:block>
                            <xsl:value-of select="nazwa"/>
                        </fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body>
                        <fo:block margin-left="120mm">Liczba produktów:</fo:block>
                        <fo:block margin-left="150mm">
                            <xsl:value-of select="liczba_produktów"/>
                        </fo:block>
                    </fo:list-item-body>
                </fo:list-item>
            </fo:list-block>
        </fo:block>
    </xsl:template>

    <xsl:template match="/sklep/data">
        <fo:block text-align="right">
            <xsl:value-of select="concat('Data wygenerowania raportu: ',.)"/>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>