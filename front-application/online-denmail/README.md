#  PDFのview部品での初期取り込み方法：
1. パッケージ追加
   npm install aws-sdk
   npm install ng2-pdf-viewer
   
2. tsconfig.app.json修正
　　"types": [] 　　ー＞　　 "types": ["node"]

3. polyfills.ts修正
  (window as any).global = window; // 最後に追加
  
4. assets/pdf.worker.min.js をコピー



