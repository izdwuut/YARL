---
layout: home
title: YARL
---
# [What is {{ site.title_short }}](https://www.youtube.com/watch?v=3rzgrP7VA_Q)
{{ site.title_short }} is going to be an epic roguelike adventure to once and forever fight off the Lich that keeps invading your home village. You are designated to descend to a dungeon filled with filthy monsters and destroy the Lich's phylactery. It seems that your folks don't like you anyways. But {{ site.title_short }} is not there yet. Now you just kill those poor Sloths.

{% assign release = site.data.releases.first %}
[Download v{{ release.version }}]({{ release.binary }}). Requires [JRE 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and will run on your toaster. I've just checked it - [IoT](https://en.wikipedia.org/wiki/Internet_of_things) toasters are officially a thing. Still better than [IoD](https://www.sec-consult.com/en/blog/2018/02/internet-of-dildos-a-long-way-to-a-vibrant-future-from-iot-to-iod/index.html).

[Release notes]({{ release.notes }}).

Source: [zip]({{ release.source_zip }}), [tar]({{ release.source_tar }}).

# Open Source to the core

{{ site.title_short }} is licensed under [a permissive MIT License](https://choosealicense.com/licenses/mit/). You are free to reuse [any piece of code](https://github.com/izdwuut/YARL) you like as long as you mention me somewhere and provide [an original license text](https://github.com/izdwuut/YARL/blob/master/LICENSE). Don't forget to let me know what you are up to - I'd love to see your accomplishment!

I organize my work using [a public Trello task board](https://trello.com/b/D2fDQmGj/yarl). To a degree I try to implement [Scrum](https://www.scrum.org/), which works pretty well. I work alone but I put some effort to write [Daily Scrum notes]({{ "sprints" | relative_url }}). I don't really stick to fixed sprint length or capacity. I run other (rather small) [side projects](https://github.com/izdwuut?tab=repositories) and have some fun with my uni. The closer I am to finishing my degree, the more fun I am having with this project. I don't feel compelled to strictly follow [Scrum Guide](https://www.scrumguides.org/). The worst that can happen is my result not being Scrum.

I am a member of [/r/roguelikedev](https://www.reddit.com/r/roguelikedev/) community. While I mostly lurk and upvote, I take the opportunity to participate in weekly [Sharing Saturday](sharing-saturday) threads. It's a great place for holding myself accountable. It's insane what these guys are capable to come up with in just a week!

Oh, I also run [a dev blog]({{ "blog" | relative_url }}). There's nothing in there yet, but I'm going to use it extensively once I have some relevant stuff to write about.

# Development stack

{{ site.title_short }} is written in Java. I had close to no experience with this programming language prior to starting the project. I mean, I created a calculator app in my high school. How hard can it be? I just thought it would be a viable choice. There is a plethora of resources on interweb and some really cool libraries and frameworks. I went through [a Trystan's tutorial](http://trystans.blogspot.com/2016/01/roguelike-tutorial-00-table-of-contents.html) and decided on a set of tools and here I am.

I went for an [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) approach. They say games don't benefit from MVC but I don't necessarily agree. It makes for a nice [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns). Roguelikes are intricate things and anything that leads to a better architecture helps. They also tend to have graphical overlays which makes it a reasonable choice.

I heard there is a nifty [ECS](https://en.wikipedia.org/wiki/Entity%E2%80%93component%E2%80%93system) design pattern and it's pretty much a game dev standard. Guess what, now I'm all in love with it. [Ashley](https://github.com/libgdx/ashley) is my weapon of choice, it resides in a model layer. The only caveat so far is tedious component mapping. Not really a concern for now, but I have my eye on it.

I utilize [LibGDX](https://libgdx.badlogicgames.com) framework, mostly because [SquidLib](https://github.com/SquidPony/SquidLib) kind of requires it. It allows me to target [every major platform](https://libgdx.badlogicgames.com/features.html). They also handle display. {{ site.title_short }} is a roguelike because I don't want to dive right into graphics stuff but I'd like the possibility. I'm fond of roguelike graphics being as simple as outputting an array of characters. This means I can twiddle more with mechanics - the heart of every game. Furthermore, the tandem provides me with some useful data structures and robust toolkit.

I might not be proficient in [JUnit](https://junit.org/junit5/), but I use it for my unit tests. I struggle to write every single one of them and I certainly plan to put some more focus on them. I'm just uncertain when it's going to happen and it bugs me. I guess not every issue can be addressed in the foreseeable future. 