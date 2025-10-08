package org.prometey.description

interface DescriptKing

interface DescriptStructure : DescriptKing {
    data object CLASS : DescriptStructure
    data object OBJECT : DescriptStructure
    data object FUNCTION : DescriptStructure
    data object LAMBDA : DescriptStructure
}

interface Primitives : DescriptKing {
    data object UNIT : Primitives
    data object BYTE : Primitives
    data object SHORT : Primitives
    data object INT : Primitives
    data object LONG : Primitives
    data object FLOAT : Primitives
    data object DOUBLE : Primitives
    data object STRING : Primitives
}