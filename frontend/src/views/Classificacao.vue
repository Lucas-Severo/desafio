<template>
    <div class="options">
        <input type="radio" id="all" name="options" @click="selectOption(1)">
        <label for="all">Mostras todas</label>
        <input type="radio" id="price" name="options" @click="selectOption(2)">  
        <label for="price">Menor Preço</label>  
        <input type="radio" id="grade" name="options" @click="selectOption(3)">
        <label for="grade">Nota Preço</label>

        <ul class="propostas" v-show="selected >= 1 && selected <= 3">
            <li class="proposta" v-for="proposta in propostas" :key="proposta.id">
                <div class="proposta__fornecedor">
                    Fornecedor: {{proposta.fornecedor}}
                </div>
                <div class="proposta__data-cadastro">
                    Data de cadastro: {{ proposta.dataCadastro}}
                </div>
                <div class="proposta__tipo">
                    Tipo de Licitacao: {{ proposta.licitacaoClassificacao }}
                </div>
                <div v-if="proposta.licitacaoClassificacao === 'NOTA_PRECO'" class="proposta__nota">
                    Nota: {{proposta.nota}}
                </div>
                <div class="proposta__preco">
                    Preço: {{proposta.preco}}
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import api from '../services/api';

export default {
    data: () => ({
        selected: 0,
        propostas__saved: [],
        propostas: [],
        menor_preco: [],
        nota_preco: []
    }),
    methods: {
        selectOption(option) {
            if(option === 1) {
                this.propostas = this.propostas__saved;
            } else if(option === 2) {
                this.propostas = this.menor_preco;
            } else if(option === 3) {
                this.propostas = this.nota_preco;
            }
            this.selected = option;
            console.log(this.propostas);
        }
    },
    mounted() {
        api.get('/proposta')
        .then(response => {
            return response.data;
        })
        .then(response => {this.propostas__saved = response})

        api.get('/proposta/classificacao/0')
        .then(response => {
            return response.data;
        })
        .then(response => {this.menor_preco = response})

        api.get('/proposta/classificacao/1')
        .then(response => {
            return response.data;
        })
        .then(response => {this.nota_preco = response})
    }
    
}
</script>

<style lang="stylus" scoped>

</style>