fun ultimaLinhaAzul(showLegend: Boolean, countLines: Int, numColumns: Int, numLines: Int): String {
    var tabuleiro = ""
    val esc: String = 27.toChar().toString()
    val end = "$esc[0m"
    val startBlue = "$esc[30;44m"
    var countColumns: Int
    val quadradoAzul = "$startBlue   $end"
    if (showLegend && countLines == numLines) {
        countColumns = 0
        do {
            tabuleiro += quadradoAzul
            countColumns++
        } while (countColumns < numColumns + 1)
        tabuleiro += quadradoAzul + "\n"
    }
    return tabuleiro
}


fun buildBoard(
    numColumns: Int, numLines: Int,
    showLegend: Boolean = false, showPieces: Boolean = false,
    pieces: Array<Pair<String, String>?>
): String {
    val esc: String = 27.toChar().toString()
    var tabuleiro = ""
    val end = "$esc[0m"
    val startBlue = "$esc[30;44m"
    val startGrey = "$esc[30;47m"//BRANCO
    val startWhite = "$esc[30;30m"//PRETO
    var quadradoAzul = ""
    var countLegenda = 0
    var countLines = 0
    var countColumns = 0
    var numero = 1
    var countPeca = 0
    var texto: String
    while (countLines < numLines) {
        if (showLegend) {
            quadradoAzul = "$startBlue   $end"
            if (countLines == 0) {
                do {
                    when {// legenda letras
                        countColumns == 0 -> {
                            tabuleiro += quadradoAzul
                        }
                        countColumns <= numColumns -> {
                            val abc = ('A'.toInt() + countLegenda).toChar()
                            tabuleiro += "$startBlue $abc $end"
                            countLegenda++
                        }
                        else -> {
                            tabuleiro += quadradoAzul }
                    }
                    countColumns++
                } while (countColumns <= numColumns + 1)
                tabuleiro += "\n"
            }
            if (countColumns != 0) {
                tabuleiro += "$startBlue $numero $end"
                numero++ } }
        countColumns = 0
        while (countColumns < numColumns) {
            texto = if (showPieces && pieces[countPeca] != null) {
                convertStringToUnicode(pieces[countPeca]?.first.toString(), pieces[countPeca]?.second.toString())
            } else { " " }
            tabuleiro += if (countLines % 2 == 0) {
                if (countColumns % 2 == 0) {
                    "$startWhite $texto $end"
                } else {
                    "$startGrey $texto $end" }
            } else {
                if (countColumns % 2 == 0) {
                    "$startGrey $texto $end"
                } else {
                    "$startWhite $texto $end" }
            }
            countPeca++
            countColumns++
        }
        if (showLegend && countColumns == numColumns && countLines >= 0 && countLines < numLines + 1) {// quadrado azul no final de cada linha
            tabuleiro += quadradoAzul
        }
        tabuleiro += "\n"
        countLines++
    }
    tabuleiro += ultimaLinhaAzul(showLegend, countLines, numColumns, numLines); return tabuleiro
}
/*Devolve uma string que contém a informação do tabuleiro | construir o tabuleiro a partir do array de peças que é passado como parâmetro.*/

fun createInitialBoard(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    return when {
        numColumns == 8 && numLines == 8 -> {
            arrayOf(
                Pair("T", "b"), Pair("H", "b"), Pair("B", "b"), Pair("Q", "b"), Pair("K", "b"), Pair("B", "b"),
                Pair("H", "b"), Pair("T", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"),
                Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, Pair("P", "w"), Pair("P", "w"),
                Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"),
                Pair("T", "w"), Pair("H", "w"), Pair("B", "w"), Pair("K", "w"), Pair("Q", "w"), Pair("B", "w"),
                Pair("H", "w"), Pair("T", "w")
            )
        }
        numColumns == 7 && numLines == 7 -> {
            arrayOf(
                Pair("T", "b"), Pair("H", "b"), Pair("B", "b"), Pair("K", "b"), Pair("B", "b"), Pair("H", "b"),
                Pair("T", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"),
                Pair("P", "b"), Pair("P", "b"), null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, Pair("P", "w"), Pair("P", "w"), Pair("P", "w"),
                Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("T", "w"), Pair("H", "w"),
                Pair("B", "w"), Pair("K", "w"), Pair("B", "w"), Pair("H", "w"), Pair("T", "w")
            )
        }
        numColumns == 6 && numLines == 6 -> {
            arrayOf(
                Pair("H", "b"), Pair("B", "b"), Pair("Q", "b"), Pair("K", "b"), Pair("B", "b"), Pair("T", "b"),
                Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"),
                null, null, null, null, null, null, null, null, null, null, null, null, Pair("P", "w"), Pair("P", "w"),
                Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("H", "w"), Pair("B", "w"),
                Pair("K", "w"), Pair("Q", "w"), Pair("B", "w"), Pair("T", "w")
            )
        }
        numColumns == 6 && numLines == 7 -> {
            arrayOf(
                Pair("T", "b"), Pair("B", "b"), Pair("Q", "b"), Pair("K", "b"), Pair("B", "b"), Pair("H", "b"),
                Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"), Pair("P", "b"),
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"), Pair("P", "w"),
                Pair("P", "w"), Pair("T", "w"), Pair("B", "w"), Pair("K", "w"), Pair("Q", "w"), Pair("B", "w"),
                Pair("H", "w")
            )
        }
        numColumns == 4 && numLines == 4 -> {
            arrayOf(
                null, null, Pair("T", "b"), Pair("B", "b"), null, null, null,
                null, null, null, null, null, Pair("T", "w"), Pair("Q", "w"),
                null, null
            )
        }
        else -> arrayOf()
//A partir do número de colunas e linhas, será necessário construir e retornar um array de Pair (peças do tabuleiro)

    }
}

fun createTotalPiecesAndTurn(numColumns: Int, numLines: Int): Array<Int?> {

    return when {
        numColumns == 8 && numLines == 8 -> {
            arrayOf(16, 16, 0)
        }
        numColumns == 7 && numLines == 7 -> {
            arrayOf(14, 14, 0)
        }
        numColumns == 6 && numLines == 6 || numColumns == 6 && numLines == 7 -> {
            arrayOf(12, 12, 0)
        }
        numColumns == 4 && numLines == 4 -> {
            arrayOf(2, 2, 0)
        }
        else -> arrayOf()
    }
// A partir do número de colunas e linhas, será necessário construir e
// retornar um array de 3 posições de Int por exemplo (qntpecasPretas,qntpecasBrancas,Turno)

}

fun convertStringToUnicode(piece: String, color: String): String {
    return when {
        piece == "P" && color == "w" -> "\u2659"
        piece == "H" && color == "w" -> "\u2658"
        piece == "K" && color == "w" -> "\u2654"
        piece == "T" && color == "w" -> "\u2656"
        piece == "B" && color == "w" -> "\u2657"
        piece == "Q" && color == "w" -> "\u2655"
        piece == "P" && color == "b" -> "\u265F"
        piece == "H" && color == "b" -> "\u265E"
        piece == "K" && color == "b" -> "\u265A"
        piece == "T" && color == "b" -> "\u265C"
        piece == "B" && color == "b" -> "\u265D"
        piece == "Q" && color == "b" -> "\u265B"
        else -> " "
    }

    //A partir do tipo de peça e da côr, devolve o Unicode respetivo.
    // Caso a peça e/ou a côr sejam inválidos, deve retornar uma String com um espaço (“ “)
}

fun getCoordinates(readText: String?): Pair<Int, Int>? {
    var letra = 0

    if (readText == null || readText.length != 2 || readText[0].toInt() !in 48..57 && //48=1 e 57=8
        (readText[1] !in 'a'..'h' || readText[1] !in 'A'..'H')
    ) {
        return null
// A partir de uma string (por exemplo “2a”),
//esta função converte as coordenadas em números (por exemplo return Pair(2,1) com a string de entrada de “2a”).
// Se as coordenadas forem inválidas, deve retornar null
// nao importa se é A ou a

    } else {
        when (readText[1]) {
            'a', 'A' -> letra = 1
            'b', 'B' -> letra = 2
            'c', 'C' -> letra = 3
            'd', 'D' -> letra = 4
            'e', 'E' -> letra = 5
            'f', 'F' -> letra = 6
            'g', 'G' -> letra = 7
            'h', 'H' -> letra = 8
        }
        return (Pair(readText[0].toInt() - 48, letra))
    }

}

fun checkRightPieceSelected(pieceColor: String, turn: Int): Boolean {
    return if (turn == 0) {
        pieceColor == "w"
    } else {
        pieceColor == "b"
    }

//Valida se a peça escolhida para mover é do respectivo jogador
}

fun isCoordinateInsideChess(coord: Pair<Int, Int>, numColumns: Int, numLines: Int): Boolean {
    if (coord.first in 1..numLines && coord.second in 1..numColumns) {
        return true
    }

    return false
//Valida se as coordenadas escolhidas estão dentro do tabuleiro
}

fun isValidTargetPiece(
    currentSelectedPiece: Pair<String, String>,
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {

    return when {
        currentSelectedPiece.first == "H" && isHorseValid(
            currentCoord, targetCoord,
            pieces, numColumns, numLines
        ) -> true

        currentSelectedPiece.first == "B" && isBishopValid(
            currentCoord, targetCoord,
            pieces, numColumns, numLines
        ) -> true

        currentSelectedPiece.first == "Q" && isQueenValid(
            currentCoord, targetCoord,
            pieces, numColumns, numLines
        ) -> true

        currentSelectedPiece.first == "K" && isKingValid(
            currentCoord, targetCoord,
            pieces, numColumns, numLines
        ) -> true

        currentSelectedPiece.first == "T" && isTowerValid(
            currentCoord, targetCoord,
            pieces, numColumns, numLines
        ) -> true

        currentSelectedPiece.first == "P" && isKnightValid(
            currentCoord,
            targetCoord, pieces, numColumns, numLines
        ) -> true

        else -> false
    }
    //Valida se o movimento da peça é válido. Se
    //sim retorna true e false caso contrário.
    //(Dica: Esta função irá ser chamada dentro
    //da função movePiece)
}

fun movePiece(
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int,
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    totalPiecesAndTurn: Array<Int>
): Boolean {

    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1
    val currentSelectedPiece = pieces[coordToPieces]

    if (isValidTargetPiece(currentSelectedPiece!!, currentCoord, targetCoord, pieces, numColumns, numLines)
        && checkRightPieceSelected(pieces[coordToPieces]?.second.toString(), totalPiecesAndTurn[2])
    ) {
        if (pieces[targetToPieces] != null && totalPiecesAndTurn[2] == 0) { //ta la um preto
            totalPiecesAndTurn[1]-- //tiras um ao preto

        } else if (pieces[targetToPieces] != null && totalPiecesAndTurn[2] == 1) {//ta la um branco
            totalPiecesAndTurn[0]--//tiras um ao branco

        }

        pieces[targetToPieces] = pieces[coordToPieces]
        pieces[coordToPieces] = null

        if (totalPiecesAndTurn[2] == 1) {
            totalPiecesAndTurn[2] = 0

        } else if (totalPiecesAndTurn[2] == 0) {
            totalPiecesAndTurn[2] = 1
        }
        return true

    }

    return false
//Esta função, irá alterar o valor do
//argumento pieces e do
//totalPiecesAndTurno. Só serão alterados
//caso o movimento das peças sejam
//válidas
}

fun startNewGame(
    whitePlayer: String,                    //É nesta função que é mostrado o tabuleiro
    blackPlayer: String,                    //jogador colocar as coordenadas de partida e de origem. Também é aqui que dá a
    pieces: Array<Pair<String, String>?>,   //opção de retornar ao menu principal,
    totalPiecesAndTurn: Array<Int?>,        //clicando na tecla “m”
    numColumns: Int,                        //e é onde pede constantemente para cada
    numLines: Int,
    showLegend: Boolean = false,
    showPieces: Boolean = false
) {
    var jogadaDo1: String
    var jogadaTargetDo1: String
    var jogadaDo2: String
    var jogadaTargetDo2: String
    val fraseBranco = "$whitePlayer, choose a piece (e.g 2D).\nMenu-> m;\n"
    val frasePreto = "$blackPlayer, choose a piece (e.g 2D).\nMenu-> m;\n"
    val fraseBrancoTarget = "$whitePlayer, choose a target piece (e.g 2D).\nMenu-> m;\n"
    val frasePretoTarget = "$blackPlayer, choose a target piece (e.g 2D).\nMenu-> m;\n"
    var cor = ""
    do {
        println(buildBoard(numColumns, numLines, showLegend, showPieces, pieces))
        if (totalPiecesAndTurn[2] == 0) {
            println(fraseBranco)
            jogadaDo1 = readLine().toString()
            if (jogadaDo1 == "m" || jogadaDo1 == "M") { return }
            else {
                if (jogadaDo1.length == 2 && (jogadaDo1[0].toString() in "1"..numLines.toString())
                    && (jogadaDo1[1] in 'A'..'@' + numColumns || jogadaDo1[1] in 'a'..'`' + numColumns)) {
                    //transforma o que ele introduz numa coisa para usar na piece
                    val coordToPiece = ((((getCoordinates(jogadaDo1)!!.first - 1) * numLines) +
                            getCoordinates(jogadaDo1)!!.second) - 1)
                    when {
                        coordToPiece in 0..(numLines * numColumns - 1) && pieces[coordToPiece] != null -> {
                            cor = pieces[coordToPiece]!!.second } }
                    if ((isCoordinateInsideChess(getCoordinates(jogadaDo1)!!, numColumns, numLines)
                                && checkRightPieceSelected(cor, totalPiecesAndTurn[2]!!))) {//ve se esta dentro, se é da cor
                        println(fraseBrancoTarget)
                        jogadaTargetDo1 = readLine().toString()
                        if (jogadaTargetDo1 == "m" || jogadaTargetDo1 == "M") { return }
                        if (jogadaTargetDo1.length == 2 && jogadaTargetDo1[0].toString() in "1"..numLines.toString()
                            && (jogadaTargetDo1[1] in 'A'..'@' + numColumns ||
                                    jogadaTargetDo1[1] in 'a'..'`' + numColumns)) {//ve se esta dentro, se é da cor na linha de baixo
                            if (isCoordinateInsideChess(getCoordinates(jogadaTargetDo1)!!, numColumns, numLines)){
                                if (!movePiece(pieces, numColumns, numLines, getCoordinates(jogadaDo1)!!,
                                        getCoordinates(jogadaTargetDo1)!!, totalPiecesAndTurn as Array<Int>)){
                                    println(respostaInvalida())}
                            } else { println(respostaInvalida()) }
                        } else { println(respostaInvalida()) }
                    } else { println(respostaInvalida()) }
                } else { println(respostaInvalida()) } }
        } else {
            println(frasePreto)
            jogadaDo2 = readLine().toString()
            if (jogadaDo2 == "m" || jogadaDo2 == "M") { return }
            else {
                if (jogadaDo2.length == 2 && jogadaDo2[0].toString() in "1"..numLines.toString()
                    && (jogadaDo2[1] in 'A'..'@' + numColumns || jogadaDo2[1] in 'a'..'`' + numColumns)) {
                    val coordToPiece2 = ((((getCoordinates(jogadaDo2)!!.first - 1) * numLines) +
                            getCoordinates(jogadaDo2)!!.second) - 1)
                    when {
                        coordToPiece2 in 0..(numLines * numColumns - 1) && pieces[coordToPiece2] != null -> {
                            cor = pieces[coordToPiece2]!!.second }}
                    if (isCoordinateInsideChess(getCoordinates(jogadaDo2)!!, numColumns, numLines)
                        && checkRightPieceSelected(cor, totalPiecesAndTurn[2]!!)) {//ve se esta dentro, se é da cor
                        println(frasePretoTarget)
                        jogadaTargetDo2 = readLine().toString()
                        if (jogadaTargetDo2 == "m" || jogadaTargetDo2 == "M") { return }
                        if (jogadaTargetDo2.length == 2 && jogadaTargetDo2[0].toString() in "1"..numLines.toString()
                            && (jogadaTargetDo2[1] in 'A'..'@' + numColumns || jogadaTargetDo2[1] in 'a'..'`' + numColumns)) {
                            if (isCoordinateInsideChess(getCoordinates(jogadaTargetDo2)!!, numColumns, numLines)) {//ve se esta dentro, se é da cor
                                if (!movePiece(pieces, numColumns, numLines, getCoordinates(jogadaDo2)!!,
                                        getCoordinates(jogadaTargetDo2)!!, totalPiecesAndTurn as Array<Int>)) {println(respostaInvalida())}
                            } else { println(respostaInvalida()) }
                        } else { println(respostaInvalida()) }
                    } else {println(respostaInvalida()) }
                } else { println(respostaInvalida()) } } }
    } while (totalPiecesAndTurn[0] != 0 && totalPiecesAndTurn[1] != 0)
    println(final(totalPiecesAndTurn, blackPlayer, whitePlayer)) }

fun final(totalPiecesAndTurn: Array<Int?>, blackPlayer: String, whitePlayer: String):String {
    if (totalPiecesAndTurn[0] == 0) {
        return "Congrats! $blackPlayer wins!"
    }
    if (totalPiecesAndTurn[1] == 0) {
        return "Congrats! $whitePlayer wins!"
    }
    return ""
}

fun isHorseValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1
    if (((pieces[coordToPieces]?.second == "b" && pieces[targetToPieces]?.second != "b") ||
                (pieces[coordToPieces]?.second == "w" && pieces[targetToPieces]?.second != "w"))
    ) {
        return when {
            currentCoord.first - 2 == targetCoord.first && currentCoord.second + 1 == targetCoord.second -> true

            currentCoord.first - 2 == targetCoord.first && currentCoord.second - 1 == targetCoord.second -> true

            currentCoord.first + 2 == targetCoord.first && currentCoord.second - 1 == targetCoord.second -> true

            currentCoord.first + 2 == targetCoord.first && currentCoord.second + 1 == targetCoord.second -> true

            currentCoord.first - 1 == targetCoord.first && currentCoord.second + 2 == targetCoord.second -> true

            currentCoord.first + 1 == targetCoord.first && currentCoord.second + 2 == targetCoord.second -> true

            currentCoord.first - 1 == targetCoord.first && currentCoord.second - 2 == targetCoord.second -> true

            currentCoord.first + 1 == targetCoord.first && currentCoord.second - 2 == targetCoord.second -> true

            else -> false
        }
    }
    return false
//Dado as coordenadas do cavalo
//(currentCoord), as coordenadas de destino
//(targetCoord) e as peças do tabuleiro
//(pieces)
}

fun isKingValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {

    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1

    if (((pieces[coordToPieces]?.second == "b" && pieces[targetToPieces]?.second != "b") ||
                (pieces[coordToPieces]?.second == "w" && pieces[targetToPieces]?.second != "w"))
    ) {
        return when {
            isKnightValid(currentCoord, targetCoord, pieces, numColumns, numLines)-> true //cima ou baixo = ao peao

            currentCoord.first == targetCoord.first && currentCoord.second == targetCoord.second + 1 -> true //uma para direita

            currentCoord.first == targetCoord.first && currentCoord.second == targetCoord.second - 1 -> true //uma para esquerda

            currentCoord.first + 1 == targetCoord.first && currentCoord.second + 1 == targetCoord.second -> true // diagonal direita baixo

            currentCoord.first - 1 == targetCoord.first && currentCoord.second + 1 == targetCoord.second -> true //diagonal direita cima

            currentCoord.first + 1 == targetCoord.first && currentCoord.second - 1 == targetCoord.second -> true //diagonal esquerda baixo

            currentCoord.first - 1 == targetCoord.first && currentCoord.second - 1 == targetCoord.second -> true // diagonal esquerda cima
            else -> false
        }
    }
    return false
//Dado as coordenadas do rei
//(currentCoord), as coordenadas de destino
//(targetCoord) e as peças do tabuleiro
//(pieces)
}

fun isTowerValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {

    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1

    if (((pieces[coordToPieces]?.second == "b" && pieces[targetToPieces]?.second != "b") ||
                (pieces[coordToPieces]?.second == "w" && pieces[targetToPieces]?.second != "w"))
    ) {
        return when {
            currentCoord.first != targetCoord.first && currentCoord.second == targetCoord.second -> true
            currentCoord.first == targetCoord.first && currentCoord.second != targetCoord.second -> true
            else -> false
        }
    }
    return false
//Dado as coordenadas da Torre
//(currentCoord), as coordenadas de destino
//(targetCoord) e as peças do tabuleiro
//(pieces)
}

fun isBishopValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1
    var countBispo = 1
    while (countBispo < numColumns) {
        if (((pieces[coordToPieces]?.second == "b" && pieces[targetToPieces]?.second != "b") ||
                    (pieces[coordToPieces]?.second == "w" && pieces[targetToPieces]?.second != "w"))
        ) {
            when {
                currentCoord.first + countBispo == targetCoord.first &&
                        currentCoord.second + countBispo == targetCoord.second -> return true //direita baixo

                currentCoord.first + countBispo == targetCoord.first &&
                        currentCoord.second - countBispo == targetCoord.second -> return true //esquerda baixo

                currentCoord.first - countBispo == targetCoord.first &&
                        currentCoord.second + countBispo == targetCoord.second -> return true //direita cima

                currentCoord.first - countBispo == targetCoord.first &&
                        currentCoord.second - countBispo == targetCoord.second -> return true //esquerda cima
            }
        }
        countBispo++
    }
    return false

//Dado as coordenadas do Bispo
//(currentCoord), as coordenadas de destino
//(targetCoord) e as peças do tabuleiro
//(pieces)
}

fun isQueenValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {

    return when {
        isBishopValid(currentCoord, targetCoord, pieces, numColumns, numLines) ||
                isTowerValid(currentCoord, targetCoord, pieces, numColumns, numLines) -> true

        else -> false
    }

//Dado as coordenadas da Rainha
//(currentCoord), as coordenadas de destino
//(targetCoord) e as peças do tabuleiro
//(pieces)
}

fun isKnightValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val coordToPieces = (numLines * (currentCoord.first - 1) + currentCoord.second) - 1
    val targetToPieces = (numLines * (targetCoord.first - 1) + targetCoord.second) - 1

    if (((pieces[coordToPieces]?.second == "b" && pieces[targetToPieces]?.second != "b") ||
                (pieces[coordToPieces]?.second == "w" && pieces[targetToPieces]?.second != "w"))
    ) {

        return when {
            targetCoord.first == currentCoord.first + 1 && currentCoord.second == targetCoord.second -> true //para baixo

            targetCoord.first == currentCoord.first - 1 && currentCoord.second == targetCoord.second -> true // para cima

            else -> false
        }
    }
    return false

//Dado as coordenadas do Peão
//(currentCoord), as coordenadas de destino
//(targetCoord), as peças do tabuleiro
//(pieces)
}

fun buildMenu(): String {
    return ("1-> Start New Game;\n2-> Exit Game.\n")
}

fun showChessLegendOrPieces(mensagem: String): Boolean? {
    return when (mensagem) {
        "y", "Y" -> true
        "n", "N" -> false
        else -> null
    }
}

fun checkName(nome: String): Boolean {
    var count = 0
    while (count < nome.length - 1) {
        if (nome[count] == ' ') {
            return !((nome[0] !in 'A'..'Z') || (nome[count + 1] !in 'A'..'Z'))
        } else count++
    }
    return false
}

fun checkIsNumber(numero: String): Boolean {
    if (numero.toIntOrNull() != null) {
        return true
    }
    return false
}

fun checkDimensoesTabuleiro(numColumns: Int, numLines: Int): Boolean {
    return when {
        numColumns == 8 && numLines == 8 -> true
        numColumns == 7 && numLines == 7 -> true
        numColumns == 6 && (numLines == 6 || numLines == 7) -> true
        numColumns == 4 && numLines == 4 -> true
        else -> false
    }

}

fun respostaInvalida():String="Invalid response."


fun main() {
    println("Welcome to the Chess Board Game!")
    do {// parte inicial (quer começar ou nao)
        var inicio = 3
        while (inicio >= 3 || inicio <= 0) {
            println(buildMenu())
            inicio = readLine()?.toIntOrNull() ?: 3
        }
        var nome1: String
        var nome2: String
        if (inicio != 2) {// corre o programa inteiro
            do {
                println("First player name?\n")
                nome1 = readLine().toString()
                if (!checkName(nome1)) {
                    println(respostaInvalida())
                }
            } while (!checkName(nome1))
            do {
                println("Second player name?\n")
                nome2 = readLine().toString()
                if (!checkName(nome2)) {
                    println(respostaInvalida())
                }
            } while (!checkName(nome2))
            var numColumns: Int
            var numLines: Int
            do {
                println("How many chess columns?\n")
                numColumns = readLine()?.toIntOrNull() ?: 0
                while (!checkIsNumber(numColumns.toString()) || numColumns < 4 || numColumns > 8) {
                    println("${respostaInvalida()}\nHow many chess columns?\n")
                    numColumns = readLine()?.toIntOrNull() ?: 0
                }
                println("How many chess lines?\n")
                numLines = readLine()?.toIntOrNull() ?: 0

                if (!checkDimensoesTabuleiro(numColumns, numLines)) {
                    println(respostaInvalida())// quando as dimensões do tabuleiro são inválidas
                }
            } while (!checkDimensoesTabuleiro(numColumns, numLines))

            val totalpecas = createTotalPiecesAndTurn(numColumns, numLines)
            val pecas: Array<Pair<String, String>?> = createInitialBoard(numColumns, numLines)
            println("Show legend (y/n)?\n")
            var showLegend = readLine().toString()
            while (showChessLegendOrPieces(showLegend) == null) {
                println("${respostaInvalida()}\nShow legend (y/n)?\n")
                showLegend = readLine().toString()
            }
            println("Show pieces (y/n)?\n")
            var showPieces = readLine().toString()
            while (showChessLegendOrPieces(showPieces) == null) {
                println("${respostaInvalida()}\nShow pieces (y/n)?\n")
                showPieces = readLine().toString()
            }

            startNewGame(
                nome1, nome2, pecas, totalpecas, numColumns, numLines,
                showChessLegendOrPieces(showLegend) ?: false,
                showChessLegendOrPieces(showPieces) ?: false
            )
        }
    } while (inicio != 2)
}