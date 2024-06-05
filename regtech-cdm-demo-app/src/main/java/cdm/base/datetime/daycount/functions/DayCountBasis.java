package cdm.base.datetime.daycount.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import javax.inject.Inject;


/**
 * Return the day count basis (the denominator of the day count fraction) for the day count fraction.
 * @version ${project.version}
 */
public class DayCountBasis implements RosettaFunction {
	
	@Inject protected DayCountBasis.DayCountBasisACT_360 dayCountBasisACT_360;
	@Inject protected DayCountBasis.DayCountBasis_30_360 dayCountBasis_30_360;
	@Inject protected DayCountBasis.DayCountBasis_30E_360 dayCountBasis_30E_360;
	@Inject protected DayCountBasis.DayCountBasisACT_365L dayCountBasisACT_365L;
	@Inject protected DayCountBasis.DayCountBasisACT_365_FIXED dayCountBasisACT_365_FIXED;
	@Inject protected DayCountBasis.DayCountBasisACT_ACT_AFB dayCountBasisACT_ACT_AFB;
	@Inject protected DayCountBasis.DayCountBasisACT_ACT_ISDA dayCountBasisACT_ACT_ISDA;
	@Inject protected DayCountBasis.DayCountBasisACT_ACT_ICMA dayCountBasisACT_ACT_ICMA;
	@Inject protected DayCountBasis.DayCountBasisCAL_252 dayCountBasisCAL_252;
	
	public Integer evaluate(DayCountFractionEnum dcf) {
		switch (dcf) {
			case ACT_360:
				return dayCountBasisACT_360.evaluate(dcf);
			case _30_360:
				return dayCountBasis_30_360.evaluate(dcf);
			case _30E_360:
				return dayCountBasis_30E_360.evaluate(dcf);
			case ACT_365L:
				return dayCountBasisACT_365L.evaluate(dcf);
			case ACT_365_FIXED:
				return dayCountBasisACT_365_FIXED.evaluate(dcf);
			case ACT_ACT_AFB:
				return dayCountBasisACT_ACT_AFB.evaluate(dcf);
			case ACT_ACT_ISDA:
				return dayCountBasisACT_ACT_ISDA.evaluate(dcf);
			case ACT_ACT_ICMA:
				return dayCountBasisACT_ACT_ICMA.evaluate(dcf);
			case CAL_252:
				return dayCountBasisCAL_252.evaluate(dcf);
			default:
				throw new IllegalArgumentException("Enum value not implemented: " + dcf);
		}
	}
	
	@ImplementedBy(DayCountBasisACT_360.DayCountBasisACT_360Default.class)
	public static abstract class DayCountBasisACT_360 implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_360Default extends DayCountBasisACT_360 {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 360;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasis_30_360.DayCountBasis_30_360Default.class)
	public static abstract class DayCountBasis_30_360 implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasis_30_360Default extends DayCountBasis_30_360 {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 360;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasis_30E_360.DayCountBasis_30E_360Default.class)
	public static abstract class DayCountBasis_30E_360 implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasis_30E_360Default extends DayCountBasis_30E_360 {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 360;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisACT_365L.DayCountBasisACT_365LDefault.class)
	public static abstract class DayCountBasisACT_365L implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_365LDefault extends DayCountBasisACT_365L {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 365;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisACT_365_FIXED.DayCountBasisACT_365_FIXEDDefault.class)
	public static abstract class DayCountBasisACT_365_FIXED implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_365_FIXEDDefault extends DayCountBasisACT_365_FIXED {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 365;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisACT_ACT_AFB.DayCountBasisACT_ACT_AFBDefault.class)
	public static abstract class DayCountBasisACT_ACT_AFB implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_ACT_AFBDefault extends DayCountBasisACT_ACT_AFB {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 365;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisACT_ACT_ISDA.DayCountBasisACT_ACT_ISDADefault.class)
	public static abstract class DayCountBasisACT_ACT_ISDA implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_ACT_ISDADefault extends DayCountBasisACT_ACT_ISDA {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 365;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisACT_ACT_ICMA.DayCountBasisACT_ACT_ICMADefault.class)
	public static abstract class DayCountBasisACT_ACT_ICMA implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisACT_ACT_ICMADefault extends DayCountBasisACT_ACT_ICMA {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 365;
				
				return basis;
			}
		}
	}
	@ImplementedBy(DayCountBasisCAL_252.DayCountBasisCAL_252Default.class)
	public static abstract class DayCountBasisCAL_252 implements RosettaFunction {
	
		/**
		* @param dcf Day count fraction.
		* @return basis The corresponding basis, typically 360 or 365.
		*/
		public Integer evaluate(DayCountFractionEnum dcf) {
			Integer basis = doEvaluate(dcf);
			
			return basis;
		}
	
		protected abstract Integer doEvaluate(DayCountFractionEnum dcf);
	
		public static class DayCountBasisCAL_252Default extends DayCountBasisCAL_252 {
			@Override
			protected Integer doEvaluate(DayCountFractionEnum dcf) {
				Integer basis = null;
				return assignOutput(basis, dcf);
			}
			
			protected Integer assignOutput(Integer basis, DayCountFractionEnum dcf) {
				basis = 252;
				
				return basis;
			}
		}
	}
}
