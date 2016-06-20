function submitForm() {
	if (confirm("Удаление вопроса приведет к удалению всех ответов. Продолжаем?")) {
		return true

	} else {
		return false
	}
}