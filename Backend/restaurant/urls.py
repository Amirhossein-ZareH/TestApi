from django.urls import path
from .views import MenuItemList

urlpatterns = [
    path('menu/', MenuItemList.as_view(), name='menu-list'),
]
