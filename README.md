# SpaceX - Android sample app
    
## App architecture   
  The app is written using the **MVVM** pattern with **Dagger2** for DI. 
  Basically the app is composed by a main component (*AppComponent*) which includes other main modules such us the *RetrofitModule*, and subcomponents such as the *MainComponent* module, which again includes its own modules.  

## App testing
There are few tests settled up in order to check: 
- the repo's responses returning the correct model and 
- a few main workflow test about the *MainViewModel* 

## Work in progress

There are few TODOs in the source code, about UI improvements and so on...
## Planning
I've identified these task to be accomplished during the development:

|phase|task|
|--|--|
| phase 0 | requirement and services analysis |
| phase 1 | high level architecture plan (which libraries for DI, network, etc...) |
| phase 2 | user journey plan |
| phase 3 | architecture implementation |
| phase 4 | UI implementation |
| phase 5 | viewModel event defined |
| phase 6 | viewModel flow implementation |
| phase 7 | test architecture implementation |
| phase 8 | test network mock |
| phase 9 | test viewModel flow |
| phase 10 | adjust viewModel based on test (custom dispatcher) |

Here is my scheduled plan and actual implementation time:

||1h|2h|3h|4h|5h|6h|7h|8h|9h|10h|11h|12h|13h|14h|15h|16h|17h|18h|19h|20h|
|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|
|planned phases|0|1|2|3|3|4|4|5|6|6|7|8|9|9|10|9|10|9|
|actual phases|0|1|2|3|4|5|6|5|6|6|7|8|9|9|10|9|10|9|9|9|

We can notice a few iterations between phases 9 and 10 because I had the need to review few viewModel source code in order to make it more testable.
