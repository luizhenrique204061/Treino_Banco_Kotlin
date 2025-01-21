import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val contaJoao = ContaBancariaScanner("João Silva", "780.450.698-50", 1000.0)
    val contaMaria = ContaBancariaScanner("Maria Silva", "411.540.698-55", 0.0)
    val contas = listOf(contaJoao, contaMaria)
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

        when (opcao) {
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
                println("Digite o CPF do destinatário:")
                val cpfDestinatario = scanner.next()
                val contaDestino = contas.find { it.cpf == cpfDestinatario }

                if (contaDestino == null) {
                    println("Conta com o CPF: $cpfDestinatario não encontrada. Retornando ao menu principal...")
                    continue // Sai da opção atual e volta ao menu principal
                }

                println("Digite o valor que deseja transferir:")
                val valorTransferencia = lerValorEntrada(scanner)
                contaJoao.transferir(valorTransferencia, contaDestino)
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

class ContaBancariaScanner(nome: String, cpf: String, saldo: Double) {
    val nome = nome
    val cpf = cpf
    var saldo = saldo

    fun depositar(valor: Double) {
        if (valor > 0) {
            saldo += valor
            println("Depósito concluído com sucesso, seu saldo atual é R$: $saldo")
        } else {
            println("Você inseriu um valor inválido. Por favor insira valores maiores que zero")
        }
    }

    fun sacar(valor: Double) {
        if (valor <= saldo) {
            println("Seu saque no valor de R$: $valor, foi concluído com sucesso")
            saldo -= valor
            println("Seu saldo atual é de: R$: $saldo")
        } else {
            println("Falha ao realizar o seu saque. O valor do saque excede o valor presente em sua conta")
        }
    }

    fun transferir(valor: Double, contaDestino: ContaBancariaScanner) {
        if (cpf == contaDestino.cpf) {
            println("Falha! Você não pode fazer uma transferência para si mesmo.")

        } else {
            if (valor <= saldo) {
                saldo -= valor
                contaDestino.saldo += valor
                println("Transferência de R$$valor concluída com sucesso para ${contaDestino.nome}.")
                println("Seu saldo atual é de: R$$saldo")
            }
        }
    }

}