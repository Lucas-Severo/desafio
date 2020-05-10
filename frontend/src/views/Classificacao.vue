<template>
    <div class="container">
        <div class="options">
            <input type="radio" id="all" name="options" @click="selectOption(1)">
            <label for="all">Mostras todas</label>
            <input type="radio" id="price" name="options" @click="selectOption(2)">  
            <label for="price">Menor Preço</label>  
            <input type="radio" id="grade" name="options" @click="selectOption(3)">
            <label for="grade">Nota Preço</label>
        </div>
        <ul class="propostas" v-show="selected >= 1 && selected <= 3">
            <li class="proposta" v-for="proposta in propostas" :key="proposta.id">
                <div class="proposta__info">
                    <div class="proposta__fornecedor">
                        Fornecedor: {{proposta.fornecedor}}
                    </div>
                    <div class="proposta__data-cadastro">
                        Data de cadastro: {{ proposta.dataCadastro}}
                    </div>
                    <div class="licitacao__descricao">
                        Licitação Descrição: {{ proposta.licitacaoDescricao }}
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
                </div>
                <div class="proposta__options">
                    <p class="proposta__delete" @click="handleDelete(proposta.id, proposta.licitacaoClassificacao)">Excluir</p>
                    <p class="proposta__update">Atualizar</p>
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
        },

        getData() {
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
        },
        
        async handleDelete(id, classificacao) {
            api.delete(`/proposta/${id}`);
            this.propostas__saved = this.propostas__saved.filter(proposta => {
                return proposta.id != id;
            });

            this.propostas = this.propostas__saved;

            if(classificacao === "MENOR_PRECO")
                this.menor_preco = this.menor_preco.filter(proposta => {
                    return proposta.id != id;
                });
            else if(classificacao === "NOTA_PRECO")
                this.nota_preco = this.nota_preco.filter(proposta => {
                    return proposta.id != id;
                });
        }
    },
    mounted() {
        this.getData();
    }
}
</script>

<style scoped>
    .container {
        padding: 1em;
    }
    .options {
        display: flex;
        justify-content: center;
    }
    label {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 40px;
        background: #fff;
        box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.5);
        cursor: pointer;
        border-radius: 4px;
    }
    input[name="options"] {
        visibility: hidden;
    }
    input[name="options"]:checked + label{
        background: #0a0;
        font-weight: bold;
        color: #FFF;
    }
    .propostas {
        padding-top: 1em;
        list-style: none;
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20px;
        width: 80%;
        margin: 0 auto;
    }

    .proposta {
       border: 1px solid #ccc;
       padding: 2em; 
       background-color: #fff;
       border-radius: 4px;
       font-size: 1.2em;
       position: relative;
       display: flex;
       flex-direction: column;
    }

    .proposta__info {
        flex: 1;
    }

    .proposta__fornecedor {
        font-size: 1.5em;
    }

    .proposta__options {
        display: flex;
        padding-top: 20px;
    }

    .proposta__delete,
    .proposta__update {
        color: rgba(0, 144, 144, 0.8);
        cursor: pointer;
        margin-right: 10px;
        font-size: 0.9em;
        line-height: 0.9em;
        font-weight: bold;
        text-transform: uppercase;
    }

    @media (max-width: 768px){
        .propostas {
            grid-template-columns: 1fr;
        }
    }
</style>