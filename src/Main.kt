import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val contaJoao = ContaBancaria("João Silva", "780.450.698-50", 1000.0)
    val contaMaria = ContaBancaria("Maria Silva", "411.540.698-55", 0.0)
    var opcao: Int


    do {
        exibirMenu()
        while (!scanner.hasNextInt()) {
            println("Opção inválida. Por favor escolha uma opção numérica")
            scanner.next()
            exibirMenu()
        }
        opcao = scanner.nextInt()
        //scanner.nextLine()

        when(opcao) {
            1 -> {
                println("Saldo atual: ${contaJoao.saldo}")
            }
            2 -> {
                println("Digite o valor do depósito:")
                val valorDeposito = lerValorEntrada(scanner)
                contaJoao.depositar(valorDeposito)
            }
            3 -> {
                println("Digite o valor do saque:")
                val valorSaque = lerValorEntrada(scanner)
                contaJoao.sacar(valorSaque)
            }
            4 -> {
                println("Digite o valor que deseja transferir")
                contaJoao.transferir(500.0, contaMaria)
            }
            5 -> {
                println("Saindo...")
            }
            else -> {
                println("Você digitou uma opção inválida")
            }
        }


    } while (opcao != 5)

    scanner.close()
}

private fun exibirMenu() {
    println("===Menu Banco===")
    println("1. Ver saldo")
    println("2. Depositar")
    println("3. Sacar")
    println("4. Transferir")
    println("5. Sair")
    println("Escolha uma opção:")
}

private fun lerValorEntrada(scanner: Scanner): Double {
    while (true) {
        var valorRecebido = scanner.nextLine()
        try {
            val valorDouble = valorRecebido.toDouble()
            return valorDouble
        } catch (e:NumberFormatException) {
            println("Digite apenas números")
        }
    }
}

class ContaBancaria(nome: String, cpf: String, saldo: Double) {
    val nome = nome
    val cpf = cpf
    var saldo = saldo

    fun depositar(valor: Double) {
        if (valor > 0) {
            saldo += valor
            println("Depósito concluído com sucesso, seu saldo atual é: $saldo")
        } else {
            println("Você inseriu um valor inválido. Por favor insira valores maiores que zero")
        }
    }

    fun sacar(valor: Double) {
        if (valor <= saldo) {
            println("Seu saque no valor de: $valor, foi concluído com sucesso")
            saldo -= valor
            println("Seu saldo atual é de: $saldo")
        } else {
            println("Falha ao realizar o seu saque. O valor do saque excede o valor presente em sua conta")
        }
    }

    fun transferir(valor: Double, contaDestino: ContaBancaria) {
        if (cpf == contaDestino.cpf) {
            println("Falha! Você não pode fazer uma transferência para sí mesmo")
        } else {
            if (valor <= saldo) {
                saldo -= valor
                println("Transferência concluída com sucesso, para: ${contaDestino.nome}\n no valor de: $saldo")
                println("Seu saldo atual é de: $saldo")
            }
        }
    }

}