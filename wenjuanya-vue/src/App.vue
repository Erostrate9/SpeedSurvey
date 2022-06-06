<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style>
#app {
  height: 100%;
}
* {
  margin: 0;
  padding: 0;
}
body {
  color: rgba(0, 0, 0, 0.65);
  font-size: 14px;
}
</style>
