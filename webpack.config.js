const HtmlWebpackPlugin = require('html-webpack-plugin');
const autoprefixer = require('autoprefixer');
const path = require('path');

module.exports = {
    entry: './src/main/ristorante/src/index.js',
    output: {
            path: path.resolve(__dirname, './src/main/resources/static/built'),
            filename: 'bundle.js',
    		publicPath: '/'
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    module: { //module is a js object...need loaders to load css along with js into final html file
        rules: [ //rules is an array of js objects
            {
                test: /\.(js|jsx)$/, //tests the file extension and applies appropriate loader
                exclude: /(node_modules)/,
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
            },
            {
                test: /\.html$/,
                use: [{
                    loader: "html-loader"
                }]
            },*/
            {
                test: /\.(jpe?g|png|PNG|gif|woff|woff2|eot|ttf|svg)(\?[a-z0-9=.]+)?$/,
                use: [{
                        loader: 'file-loader',
                                options: {
                                  outputPath: 'images',
                                }

                    },
                    {
                        loader: 'url-loader?limit=100000',
                                options: {
                                  outputPath: 'images',
                                }
                    }
                ]
            }
        ]
    },
    plugins: [
        /*new HtmlWebpackPlugin({
            template: "./src/main/resources/static/index.html",
            filename: "./index.html"
        }) *///generate index.html file in dist folder for you and inject link and script tags into its body
    ]
};