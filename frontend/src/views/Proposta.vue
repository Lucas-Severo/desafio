<template>
    <div class="container">
        <div class="options">
            <input type="radio" id="all" name="options" @click="selectOption(1)">
            <label class="option" for="all">Mostrar</label>
            <input type="radio" id="cadastrar" name="options" @click="selectOption(2)">  
            <label class="option" for="cadastrar">Cadastrar</label>
            <input type="radio" id="classificar" name="options" @click="selectOption(3)">  
            <label class="option" for="classificar">Classificar</label>  
        </div>

        <ul class="propostas" v-show="selected == 1 || selected == 3">
            <li class="proposta" v-for="proposta in dados" :key="proposta.id">
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
                        Nota: {{proposta.nota.toFixed(2)}}
                    </div>
                    <div class="proposta__preco">
                        Preço: R${{proposta.preco.toFixed(2).replace('.', ',')}}
                    </div>
                </div>
                <div class="proposta__options">
                    <p class="proposta__delete" @click="handleDelete(proposta.id)">Excluir</p>
                    <p class="proposta__update">Atualizar</p>
                </div>
            </li>
        </ul>

        <div class="form" v-show="selected == 2">
            <form action="" method="post" @submit.prevent="handleSubmit">
                <h1 class="form__title">Cadastrar Proposta</h1>
                <label class="form__text" for="desc">Fornecedor</label>
                <input type="text" value="fornecedor" id="desc" v-model="fornecedor" required>
                
                <label class="form__text" for="price">Preço</label>
                <input type="number" value="price" id="price" step="0.01" v-model="price" required>
                
                <label v-if="tipo.match('NOTA_PRECO')" class="form__text" for="grade">Nota</label>
                <input v-if="tipo.match('NOTA_PRECO')" type="number" value="nota" step="0.01" id="grade" v-model="nota" required> 

                <button type="input" class="form__button">Cadastrar</button>
            </form>
        </div>
    </div>
</template>

<script>
import api from '../services/api';

export default {
    data: () => ({
        dados: [],
        propostas: [],
        propostas__classified: [],
        licitacoes: [],
        fornecedor: "",
        price: null,
        licitacaoPrice: "",
        tipo: "",
        key: null,
        nota: null,
        selected: 1
    }),
    methods: {
        selectOption(option) {
            this.selected = option;

            if(option === 1)
                this.dados = this.propostas;
            else if(option === 3)
                this.dados = this.propostas__classified;
        },
        async handleSubmit() {
            await api.post('/proposta', {
                "fornecedor": this.fornecedor,
                "nota": this.nota ? Number(this.nota) : null,
                "preco": this.price,
                "licitacaoId": this.key
            })

            await api.get(`/proposta?id=${this.key}`)
            .then(response => {
                this.propostas = response.data;
            })

            await api.get(`/proposta/classificacao?id=${this.key}`)
            .then(response => {
                this.propostas__classified = response.data;
            })

            this.selected = 1;
        },
        handleDelete(id) {
            api.delete(`/proposta/${id}`);
            this.propostas = this.propostas.filter(proposta => {
                return proposta.id != id;
            });
        }
    },
    mounted() {
        this.key = this.$route.params.licitacaoId;
        this.tipo = this.$route.params.licitacaoClassificacao;

        api.get(`/proposta?id=${this.$route.params.licitacaoId}`)
        .then(response => {
            return response.data;
        })
        .then(response => {this.propostas = response; this.dados = response});

        api.get(`/proposta/classificacao?id=${this.key}`)
        .then(response => {
            this.propostas__classified = response.data;
        });
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
    label.option {
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

    .form {
        padding-top: 20px;
        display: flex;
        justify-content: center;
        text-align: center;
    }

    .form form {
        display: flex;
        flex-direction: column;
        text-align: left;
    }

    .form__text,
    .form__button {
        margin-top: 1em;
    }

    input,
    select,
    button {
        padding: 0.8em;
    }

    button {
        border-radius: 4px;
        border: none;
        cursor: pointer;
        background: #00AA00DD;
        color: #FFF;
        font-weight: bold;
        font-size: 1em;
    }
</style>