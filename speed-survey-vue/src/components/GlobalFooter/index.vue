<template>
  <global-footer>
    <template v-slot:links>
      <a href="#" target="_blank">WenJuanYa</a>
      <a href="https://github.com/wenjuanya" target="_blank">Github</a>
      <a href="https://github.com/yyangjiao" target="_blank">@yangjiao</a>
    </template>
    <template v-slot:copyright>
      <span
        >速填 |
        <a href="https://github.com/yyangjiao" target="_blank"
          >yangjiao</a
        ></span
      >
    </template>
  </global-footer>
</template>

<script>
import { GlobalFooter } from "@ant-design-vue/pro-layout";

export default {
  name: "ProGlobalFooter",
  components: {
    GlobalFooter,
  },
};
</script>
