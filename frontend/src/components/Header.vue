<template>
    <header class="header">
        <router-link to="/" class="title">Desafio</router-link>
        <nav class="menu">
            <router-link to="/licitacoes">Licitações</router-link>
            <router-link to="/propostas">Propostas</router-link>
            <router-link to="/classificar">Classificar</router-link>
        </nav>
        
    </header>
</template>

<script>
export default {
    
}
</script>

<style scoped>
    .header {
        width: 100%;
        height: 80px;
        background: #FFF;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        padding: 0 2.5em;
        border-bottom: 1px solid #ccc;
    }
    a {
        margin-right: 20px;
        text-decoration: none;
        color: #222;
        text-transform: uppercase;
        transition: all .5s;
    }
    .menu a:hover {
        text-decoration: underline;
    }
    .title {
        text-transform: uppercase;
        font-weight: bold;
        font-size: 2em;
    }
</style>