<template>
  <div class="content-wrap">
    <div id="main">
      <div class="fof">
        <h1>Error 404</h1>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

#main{
  display: table;
  width: 100%;
  height: 100vh;
  text-align: center;
  transition: all 0.6s;
}

.fof{
  display: table-cell;
  vertical-align: middle;
}

.fof h1{
  font-family: 'Lato', sans-serif;
  color: #888;
  font-size: 50px;
  display: inline-block;
  padding-right: 12px;
  animation: type .5s alternate infinite;
}

@keyframes type{
  from{box-shadow: inset -3px 0px 0px #888;}
  to{box-shadow: inset -3px 0px 0px transparent;}
}
</style>
