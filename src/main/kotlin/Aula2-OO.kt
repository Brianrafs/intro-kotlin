package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 11) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                println("Digite o nome do Cachorro:")
                val nomeAnimal = readLine()
                println("Digite a idade de $nomeAnimal:")
                val idadeAnimal = readLine()?.toIntOrNull() ?: 0
                val cachorro = Cachorro(idadeAnimal, Cor.BRANCO)
                if (nomeAnimal != null) {
                    cachorro.nome = nomeAnimal
                }
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                println("Digite o nome do Gato:")
                val nomeAnimal = readLine()
                println("Digite a idade de $nomeAnimal:")
                val idadeAnimal = readLine()?.toIntOrNull() ?: 0
                val gato = Gato(idadeAnimal, Cor.PRETO)
                if (nomeAnimal != null) {
                    gato.nome = nomeAnimal
                }
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                println("Digite o nome do Passaro:")
                val nomeAnimal = readLine()
                println("Digite a idade de $nomeAnimal:")
                val idadeAnimal = readLine()?.toIntOrNull() ?: 0
                val passaro = Passaro(idadeAnimal, Cor.AMARELO)
                if (nomeAnimal != null) {
                    passaro.nome = nomeAnimal
                }
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            6 -> {
                println("Digite o nome do animal a remover:")
                var animalARemover = readLine()
                if (animalARemover != null) {
                    repositorioAnimal.remover(animalARemover)
                }
            }
            7 -> {
                val cor = readLine()?.let { Cor.valueOf(it) }
                cor?.let { repositorioAnimal.listarPorCor(it) }
            }
            8 -> {
                val idade = readLine()?.toIntOrNull()
                idade?.let { repositorioAnimal.listarPorIdade(it) }
            }
            9 -> {
                val nomeAnimal = readLine()
                val animalEncontrado = nomeAnimal?.let { repositorioAnimal.buscarPorNome(it) }
                if (animalEncontrado != null) {
                    println("Animal encontrado: ${animalEncontrado.nome}")
                } else {
                    println("Animal não encontrado.")
                }
            }
            10 -> {

                println("Digite o nome do Humano:")
                val nomeAnimalHumano = readLine()
                println("Digite a idade de $nomeAnimalHumano:")
                val idadeAnimal = readLine()?.toIntOrNull() ?: 0
                val pessoa = Homem(idadeAnimal, Cor.AMARELO)
                if (nomeAnimalHumano != null) {
                    pessoa.nome = nomeAnimalHumano
                }
                repositorioAnimal.adicionar(pessoa)
            }
        }

    }
}

abstract class Animal(var idade: Int, var cor: Cor) {
    open var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int {
        return idade*7
    }
}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(idade: Int, cor : Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor : Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

enum class Cor{
    BRANCO, AMARELO, PRETO, CINZA
}

fun menu() {
    println("1 - Criar Cachorro")
    println("2 - Criar Gato")
    println("3 - Criar Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Remover animal")
    println("7 - Listar por cor")
    println("8 - Listar por idade")
    println("9 - Buscar por nome")
    println("10 - Criar Humano")
    println("11 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun remover(nome: String) {
        val animalARemover = animais.find { it.nome.equals(nome, ignoreCase = true) }
        if (animalARemover != null) {
            animais.remove(animalARemover)
            println("$nome removido com sucesso!")
        } else {
            println("Animal com o nome $nome não encontrado na lista.")
        }
    }


    fun listarPorCor(cor: Cor) {
        val animaisPorCor = animais.filter { it.cor == cor }
        if (animaisPorCor.isEmpty()) {
            println("Não há animais com a cor $cor na lista.")
        } else {
            println("Animais com a cor $cor:")
            animaisPorCor.forEach { println(it.nome) }
        }
    }

    fun listarPorIdade(idade: Int) {
        val animaisPorIdade = animais.filter { it.idade == idade }
        if (animaisPorIdade.isEmpty()) {
            println("Não há animais com $idade anos na lista.")
        } else {
            println("Animais com $idade anos:")
            animaisPorIdade.forEach { println(it.nome) }
        }
    }

    fun buscarPorNome(nome: String): Animal? {
        return animais.find { it.nome == nome }
    }

}


class Homem(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun idadeEmAnosHumanos(): Int {
        return idade
    }

    override fun emitirSom() {
        println("Oi, eu sou um humano.")
    }
}
