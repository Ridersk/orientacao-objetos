# Orientação a Objetos - Exercício de Programação 1


**Universidade de Brasília - Campus Gama**  
*Autor* = Lucas Maciel Aguiar 170070735  
*Turma* = Turma B 2018/1 - Prof.Renato Sampaio  

### Leitura de Imagens e Copia de Imagens

O programa realiza leitura de imagens existentes no formato pgm e ppm e copia com o nome do arquivo desejado  

### Criptografia

O programa realiza descriptografia de imagens pgm que estão criptografados com a tecnica "*Ceasar Cipher*", e imagens ppm que estão criptogrados com a tecnica "*Keyword Cipher*".  

##### Ceasar Cipher (PGM)

Neste formato, é fornecido no comentário "#" do cabeçalho da imagem  
o pixel de inicio da mensagem, o tamanho da mensagem e quantos caracteres no alfabeto foi deslocado na criptografia.  

##### Keyword Cipher (PPM)

Neste formato, é fornecido no comentário "#" do cabeçalho da imagem o pixel de inicio da mensagem, o tamanho da mensagem e a keyword usada na criptografia da mesma  

###### Observações

internamente no código do programa, no arquivo main,  forão definidos os seguintes tipos de erro (exceções), possivelmente gerados, nos arquivos das classes imagem.cpp pgm.cpp, ppm.cpp  
__-1__ - <em>Falha ao ler arquivo<em>: Quando usuário digita o nome ou caminho de arquivo inexistente  
__-2__ - <em>Falha ao gravar arquivo<em>: Quando o programa não consegue fazer a gravação de um arquivo copiado em memória  
__-3__ - <em>Imagem nao e do tipo selecionado<em>: Quando se seleciona **2** (*modo de descriptografia*) e depois escolhe um tipo de imagem, porém escolhe um caminho ou nome de arquivo que não é do tipo selecionado  
__-4__ - <em>Imagem invalida<em> : Quando o usuário escolhe uma imagem para descriptografar que não seja nem do tipo PGM ou PPM, ou que nao contenha informações sobre altura, largura e pixel maximo  
__-5__ - <em>Parametros insuficientes para decodificacao<em>: Quando os parametros fornecidos no comentário da imagem (exemplo: "inicio da mensagem", "tamanho da mensagem", "deslocamento de caractere", para imagens tipo PGM)  
__default__ - <em>Erro indefinido<em>: Erro desconhecido e/ou inesperado  

### Funcionamento do Programa

Na tela inicial do programa é pedido que se escolha uma opção de 0 a 2  
__0__ - Fecha o Programa  
__1__ - Escolhe o modo de copia de imagens  
   * __path__ Deve ser digitado o caminho absoluto ou o nome de uma imagem na raiz do programa  
   
   
__2__ - Escolhe o modo de descriptografia de imagens  
   * _Dentro deste modo_:  
       * __1__ - Para imagens PGM  
       * __2__ - Para imagens PPM  
	    * __path__ - Deve ser digitado o caminho absoluto ou o nome de uma imagem na raiz do programa  




