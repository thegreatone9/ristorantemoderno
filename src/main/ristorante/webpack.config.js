const HtmlWebpackPlugin = require('html-webpack-plugin');
const autoprefixer = require('autoprefixer');
const path = require('path');

module.exports = {
    entry: 'C:/Users/Musa Shakib/Documents/Programs/Projects/Job Application Portal/backend/src/main/ristorante/src/index.js',
    output: {
        filename: 'main.js',
        path: path.resolve('C:/Users/Musa Shakib/Documents/Programs/Projects/Job Application Portal/backend/src/main/ristorante/build), //(first arg refers to current path). Need path resolve to convert to absolute path.
        publicPath: '/' //need to specify to webpack dev-server to not ignore the path
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    module: { //module is a js object...need loaders to load css along with js into final html file
        rules: [ //rules is an array of js objects
            {
                test: /\.(js|jsx)$/, //tests the file extension and applies appropriate loader
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"],
                        plugins: ["@babel/plugin-proposal-class-properties"]
                    }
                }]
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            /*{
                test: /\.(css|scss|less)$/,
                use: [ //an array of loaders

                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            modules: {
                                localIdentName: "[name]__[local]___[hash:base64:5]",
                            },
                            sourceMap: true
                        }
                    },

                    {
                        loader: 'sass-loader'
                    },
                    {
                        loader: 'postcss-loader',
                        options: {
                            ident: 'postcss',
                            plugins: () => [
                                require('precss'),
                                autoprefixer({})
                            ]
                        }
                    },


                ]
            },*/
            {
                test: /\.html$/,
                use: [{
                    loader: "html-loader"
                }]
            },
            {
                test: /\.(jpe?g|png|PNG|gif|woff|woff2|eot|ttf|svg)(\?[a-z0-9=.]+)?$/,
                use: [{
                        loader: 'file-loader'
                    },
                    {
                        loader: 'url-loader?limit=100000'
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./src/index.html",
            filename: "./index.html"
        }) //generate index.html file in dist folder for you and inject link and script tags into its body
    ]
};