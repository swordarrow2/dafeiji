package cs;

public class MathHelper {
	public final float E = 2.718282f;
	public final float Log2E = 1.442695f;
	public final float Log10E = 0.4342945f;
	public final float Pi = 3.141593f;
	public final float TwoPi = 6.283185f;
	public final float PiOver2 = 1.570796f;
	public final float PiOver4 = 0.7853982f;
	public static float ToRadians(float degrees) {
		return degrees * ((float)Math.PI / 180f);
	}
	public static float ToDegrees(float radians) {
		return radians * 57.29578f;
	}
	public static float Distance(float value1, float value2) {
		return Math.abs(value1 - value2);
	}
	public static float Min(float value1, float value2) {
		return Math.min(value1, value2);
	}
	public static float Max(float value1, float value2) {
		return Math.max(value1, value2);
	}
	public static float Clamp(float value, float min, float max) {
		value = value > (double)max ? max : value;
		value = value < (double)min ? min : value;
		return value;
	}
	public static float Lerp(float value1, float value2, float amount) {
		return value1 + (value2 - value1) * amount;
	}
	public static float Barycentric(float value1, float value2, float value3, float amount1, float amount2) {
		return (float)(value1 + amount1 * (value2 - (double)value1) + amount2 * (value3 - (double)value1));
	}
	public static float SmoothStep(float value1, float value2, float amount) {
		float num = Clamp(amount, 0.0f, 1f);
		return Lerp(value1, value2, (float)(num * (double)num * (3.0 - 2.0 * num)));
	}
	public static float CatmullRom(float value1, float value2, float value3, float value4, float amount) {
		float num1 = amount * amount;
		float num2 = amount * num1;
		return (float)(0.5 * (2.0 * value2 + (-value1 + (double)value3) * amount + (2.0 * value1 - 5.0 * value2 + 4.0 * value3 - value4) * num1 + (-value1 + 3.0 * value2 - 3.0 * value3 + value4) * num2));
	}
	public static float Hermite(float value1, float tangent1, float value2, float tangent2, float amount) {
		float num1 = amount;
		float num2 = num1 * num1;
		float num3 = num1 * num2;
		float num4 = (float)(2.0 * num3 - 3.0 * num2 + 1.0);
		float num5 = (float)(-2.0 * num3 + 3.0 * num2);
		float num6 = num3 - 2f * num2 + num1;
		float num7 = num3 - num2;
		return (float)(value1 * (double)num4 + value2 * (double)num5 + tangent1 * (double)num6 + tangent2 * (double)num7);
	}
	public static float WrapAngle(float angle) {
		angle = (float)Math.IEEEremainder(angle, 6.28318548202515);
		if (angle <= -3.14159274101257) {
			angle += 6.283185f;
		} else if (angle > 3.14159274101257) {
			angle -= 6.283185f;
		}
		return angle;
	}
}
