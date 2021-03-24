const path = require("path");
const webpack = require("webpack");
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');

module.exports = {
    entry: "./src/index.js",
    mode: "development",
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules|bower_components)/,
                loader: "babel-loader",
                options: { presets: ["@babel/env", "@babel/react"] }
            },
            {
                test: /\.css$/,
                use: ['style-loader','css-loader']
                // use: ExtractTextPlugin.extract(
                //     {
                //         fallback: 'style-loader',
                //         use: ['css-loader']
                //     })
            }
        ]
    },
    resolve: {
        alias: {
          js : path.resolve(__dirname, 'src/js/'),
          css : path.resolve(__dirname, 'src/css/'),
          node_modules: path.resolve(__dirname, 'node_modules/')
        },
        extensions: ["*", ".js", ".jsx"]
    },
    output: {
        path: path.resolve(__dirname, "build/"),
        publicPath: "/resources/",
        filename: "js/bundle.js"
    },
    devServer: {
        contentBase: path.join(__dirname, "public/"),
        port: 3000,
        publicPath: "http://localhost:3000/resources/",
        hotOnly: true,
        historyApiFallback: true,
    },
    plugins: [new webpack.HotModuleReplacementPlugin(),
        // new ExtractTextPlugin({filename: 'css/styles.css'}),
        // new OptimizeCssAssetsPlugin()
    ]
};