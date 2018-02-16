---
layout: page
title: Sprints
---
<ul class="post-list">
	{% assign sprints =  site.sprints|group_by:"title"|reverse %}
	{% for sprint in sprints %}
		{% for item in sprint.items %}
			{% if item.goal %}
				{% include sprint.html goal=item.goal title=item.title content=item.content %}
			{% else %}
				{% include sprint.html title=item.title content=item.content %}
			{% endif %}
		{% endfor %}
	{% endfor %}
</ul>
