---
layout: page
title: Sprints
---
<ul class="post-list">
	{% assign sprints =  site.sprints|group_by:"title"|reverse %}
	{% for sprint in sprints %}
		{% for item in sprint.items %}
			<li>
				<h3>
					<a class="post-link" href="{{ item.url | relative_url }}">
					Sprint #{{ item.title }}
					</a>
				</h3>
				{% if item.goal %}
					<p>
						<span class="post-meta">Goal: {{ item.goal }}</span>
					</p>
				{% endif %}
				{{ item.content }}
				{% assign notes = site.notes|where:"sprint",item.title|reverse %}
				<ul>
					{% for note in notes %}
						<li>
							<a href="{{ note.url | relative_url }}">
								{{ note.date | date: "%Y-%m-%d" }}
							</a>
						</li>
					{% endfor %}
				</ul>
			</li>
		{% endfor %}
	{% endfor %}
</ul>
