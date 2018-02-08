---
layout: page
title: Sharing Saturday
---
Sharing Saturday is a weekly event taking place on a [/r/roguelikedev](https://www.reddit.com/r/roguelikedev/) subreddit. It's like a a sprint review held every week. I run them anyways, so it comes naturally to me. Thanks to [/u/Kyzrati](https://www.reddit.com/user/Kyzrati/) for holding these. While I mostly lurk, I always rush for this particual thread.

<ul>
{% assign saturdays =  site.sharing_saturdays|group_by:"title"|reverse %}
{% for saturday in saturdays %}
{% for item in saturday.items %}
<li><a href="{{ item.link }}">Sharing Saturday #{{ item.title }}</a></li>
{% endfor %}


{% endfor %}
</ul>
