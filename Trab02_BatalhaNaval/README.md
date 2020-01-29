# Orientação a Objetos - Exercício de Programação 2 (Batalha Naval 2.0)  


**Universidade de Brasília - Campus Gama**  
*Lucas Maciel Aguiar 170070735*  
*Turma* = Turma B 2018/1 - Prof.Renato Sampaio  

### Jogo Batalha Naval 2.0  

Desenvolvido com a IDE Eclipse.  
Jogo baseado nas regras do tradicional jogo de Batalha Naval, porém é jogado por apenas 1 pessoa, com objetivo de destruir todas as embarcações escondidas  
sem que os fundos ou pontos de habilidade do jogo se esgotem.  

### Mapas  

Quando jogo é iniciado, o programa verifica os mapas com os nomes padrão no estilo map_i.txt (i = numero do mapa), com formato txt e com palavras chave, depois  
de "#" para identificar a matriz do tabuleiro com as embarcacoes escondidas, gerando uma escolha randomica do mapa.  
O formato como é mostrado na tela ta,mbém depende do tamanho do tabuleiro, alterando o tamanho dos blocos e centralizando  

### Fundos (Ataque Disponivel)  

A quantidade de ataques e/ ou pontos de habilidade é definida pela quantidade de embarcações e o tamanho do tabuleiro, utilizando uma proporção justa para todos  
os mapa: *embarcacoes + (tamanho Tab - embarcacoes)/3*   

### Pontuação  

A pontuação é definida pelo tamanho da embarcacao encontrada: *tamEmbarc x 2*  
Exemplo = tamanho 1 = 2 pontos, tamanho 2 = 4 pontos,...  


### Funcionamento do Jogo  

#Na tela inicial do jogo tem um breve tela de menu com opções  
__1__ - Iniciar Jogo  
__2__ - Visualizar Rank com os mapas armazenados  
#Na tela de Jogo pode visualizar  
   * _Nome do Mapa atual_;  
   * _Pontos Parciais_;  
   * _Ataques Disponiveis_;  
   * _Habilidades Disponiveis_;  
   * _Embarcações Restantes_:  



