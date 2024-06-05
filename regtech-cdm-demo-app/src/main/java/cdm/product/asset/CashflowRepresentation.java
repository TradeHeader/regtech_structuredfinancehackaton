package cdm.product.asset;

import cdm.product.asset.CashflowRepresentation;
import cdm.product.asset.CashflowRepresentation.CashflowRepresentationBuilder;
import cdm.product.asset.CashflowRepresentation.CashflowRepresentationBuilderImpl;
import cdm.product.asset.CashflowRepresentation.CashflowRepresentationImpl;
import cdm.product.asset.meta.CashflowRepresentationMeta;
import cdm.product.common.schedule.PaymentCalculationPeriod;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the cashflow representation of a swap trade.
 * @version ${project.version}
 */
@RosettaDataType(value="CashflowRepresentation", builder=CashflowRepresentation.CashflowRepresentationBuilderImpl.class, version="${project.version}")
public interface CashflowRepresentation extends RosettaModelObject {

	CashflowRepresentationMeta metaData = new CashflowRepresentationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A true/false flag to indicate whether the cashflows match the parametric definition of the stream, i.e. whether the cashflows could be regenerated from the parameters without loss of information.
	 */
	Boolean getCashflowsMatchParameters();
	/**
	 * The adjusted payment date and associated calculation period parameters required to calculate the actual or projected payment amount. A list of payment calculation period elements may be ordered in the document by ascending adjusted payment date. An FpML document containing an unordered list of payment calculation periods is still regarded as a conformant document.
	 */
	List<? extends PaymentCalculationPeriod> getPaymentCalculationPeriod();

	/*********************** Build Methods  ***********************/
	CashflowRepresentation build();
	
	CashflowRepresentation.CashflowRepresentationBuilder toBuilder();
	
	static CashflowRepresentation.CashflowRepresentationBuilder builder() {
		return new CashflowRepresentation.CashflowRepresentationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CashflowRepresentation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CashflowRepresentation> getType() {
		return CashflowRepresentation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("cashflowsMatchParameters"), Boolean.class, getCashflowsMatchParameters(), this);
		processRosetta(path.newSubPath("paymentCalculationPeriod"), processor, PaymentCalculationPeriod.class, getPaymentCalculationPeriod());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashflowRepresentationBuilder extends CashflowRepresentation, RosettaModelObjectBuilder {
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder getOrCreatePaymentCalculationPeriod(int _index);
		List<? extends PaymentCalculationPeriod.PaymentCalculationPeriodBuilder> getPaymentCalculationPeriod();
		CashflowRepresentation.CashflowRepresentationBuilder setCashflowsMatchParameters(Boolean cashflowsMatchParameters);
		CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(PaymentCalculationPeriod paymentCalculationPeriod0);
		CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(PaymentCalculationPeriod paymentCalculationPeriod1, int _idx);
		CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(List<? extends PaymentCalculationPeriod> paymentCalculationPeriod2);
		CashflowRepresentation.CashflowRepresentationBuilder setPaymentCalculationPeriod(List<? extends PaymentCalculationPeriod> paymentCalculationPeriod3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("cashflowsMatchParameters"), Boolean.class, getCashflowsMatchParameters(), this);
			processRosetta(path.newSubPath("paymentCalculationPeriod"), processor, PaymentCalculationPeriod.PaymentCalculationPeriodBuilder.class, getPaymentCalculationPeriod());
		}
		

		CashflowRepresentation.CashflowRepresentationBuilder prune();
	}

	/*********************** Immutable Implementation of CashflowRepresentation  ***********************/
	class CashflowRepresentationImpl implements CashflowRepresentation {
		private final Boolean cashflowsMatchParameters;
		private final List<? extends PaymentCalculationPeriod> paymentCalculationPeriod;
		
		protected CashflowRepresentationImpl(CashflowRepresentation.CashflowRepresentationBuilder builder) {
			this.cashflowsMatchParameters = builder.getCashflowsMatchParameters();
			this.paymentCalculationPeriod = ofNullable(builder.getPaymentCalculationPeriod()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("cashflowsMatchParameters")
		public Boolean getCashflowsMatchParameters() {
			return cashflowsMatchParameters;
		}
		
		@Override
		@RosettaAttribute("paymentCalculationPeriod")
		public List<? extends PaymentCalculationPeriod> getPaymentCalculationPeriod() {
			return paymentCalculationPeriod;
		}
		
		@Override
		public CashflowRepresentation build() {
			return this;
		}
		
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder toBuilder() {
			CashflowRepresentation.CashflowRepresentationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CashflowRepresentation.CashflowRepresentationBuilder builder) {
			ofNullable(getCashflowsMatchParameters()).ifPresent(builder::setCashflowsMatchParameters);
			ofNullable(getPaymentCalculationPeriod()).ifPresent(builder::setPaymentCalculationPeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashflowRepresentation _that = getType().cast(o);
		
			if (!Objects.equals(cashflowsMatchParameters, _that.getCashflowsMatchParameters())) return false;
			if (!ListEquals.listEquals(paymentCalculationPeriod, _that.getPaymentCalculationPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashflowsMatchParameters != null ? cashflowsMatchParameters.hashCode() : 0);
			_result = 31 * _result + (paymentCalculationPeriod != null ? paymentCalculationPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashflowRepresentation {" +
				"cashflowsMatchParameters=" + this.cashflowsMatchParameters + ", " +
				"paymentCalculationPeriod=" + this.paymentCalculationPeriod +
			'}';
		}
	}

	/*********************** Builder Implementation of CashflowRepresentation  ***********************/
	class CashflowRepresentationBuilderImpl implements CashflowRepresentation.CashflowRepresentationBuilder {
	
		protected Boolean cashflowsMatchParameters;
		protected List<PaymentCalculationPeriod.PaymentCalculationPeriodBuilder> paymentCalculationPeriod = new ArrayList<>();
	
		public CashflowRepresentationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashflowsMatchParameters")
		public Boolean getCashflowsMatchParameters() {
			return cashflowsMatchParameters;
		}
		
		@Override
		@RosettaAttribute("paymentCalculationPeriod")
		public List<? extends PaymentCalculationPeriod.PaymentCalculationPeriodBuilder> getPaymentCalculationPeriod() {
			return paymentCalculationPeriod;
		}
		
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder getOrCreatePaymentCalculationPeriod(int _index) {
		
			if (paymentCalculationPeriod==null) {
				this.paymentCalculationPeriod = new ArrayList<>();
			}
			PaymentCalculationPeriod.PaymentCalculationPeriodBuilder result;
			return getIndex(paymentCalculationPeriod, _index, () -> {
						PaymentCalculationPeriod.PaymentCalculationPeriodBuilder newPaymentCalculationPeriod = PaymentCalculationPeriod.builder();
						return newPaymentCalculationPeriod;
					});
		}
		
	
		@Override
		@RosettaAttribute("cashflowsMatchParameters")
		public CashflowRepresentation.CashflowRepresentationBuilder setCashflowsMatchParameters(Boolean cashflowsMatchParameters) {
			this.cashflowsMatchParameters = cashflowsMatchParameters==null?null:cashflowsMatchParameters;
			return this;
		}
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(PaymentCalculationPeriod paymentCalculationPeriod) {
			if (paymentCalculationPeriod!=null) this.paymentCalculationPeriod.add(paymentCalculationPeriod.toBuilder());
			return this;
		}
		
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(PaymentCalculationPeriod paymentCalculationPeriod, int _idx) {
			getIndex(this.paymentCalculationPeriod, _idx, () -> paymentCalculationPeriod.toBuilder());
			return this;
		}
		@Override 
		public CashflowRepresentation.CashflowRepresentationBuilder addPaymentCalculationPeriod(List<? extends PaymentCalculationPeriod> paymentCalculationPeriods) {
			if (paymentCalculationPeriods != null) {
				for (PaymentCalculationPeriod toAdd : paymentCalculationPeriods) {
					this.paymentCalculationPeriod.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("paymentCalculationPeriod")
		public CashflowRepresentation.CashflowRepresentationBuilder setPaymentCalculationPeriod(List<? extends PaymentCalculationPeriod> paymentCalculationPeriods) {
			if (paymentCalculationPeriods == null)  {
				this.paymentCalculationPeriod = new ArrayList<>();
			}
			else {
				this.paymentCalculationPeriod = paymentCalculationPeriods.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public CashflowRepresentation build() {
			return new CashflowRepresentation.CashflowRepresentationImpl(this);
		}
		
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder prune() {
			paymentCalculationPeriod = paymentCalculationPeriod.stream().filter(b->b!=null).<PaymentCalculationPeriod.PaymentCalculationPeriodBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCashflowsMatchParameters()!=null) return true;
			if (getPaymentCalculationPeriod()!=null && getPaymentCalculationPeriod().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashflowRepresentation.CashflowRepresentationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CashflowRepresentation.CashflowRepresentationBuilder o = (CashflowRepresentation.CashflowRepresentationBuilder) other;
			
			merger.mergeRosetta(getPaymentCalculationPeriod(), o.getPaymentCalculationPeriod(), this::getOrCreatePaymentCalculationPeriod);
			
			merger.mergeBasic(getCashflowsMatchParameters(), o.getCashflowsMatchParameters(), this::setCashflowsMatchParameters);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashflowRepresentation _that = getType().cast(o);
		
			if (!Objects.equals(cashflowsMatchParameters, _that.getCashflowsMatchParameters())) return false;
			if (!ListEquals.listEquals(paymentCalculationPeriod, _that.getPaymentCalculationPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashflowsMatchParameters != null ? cashflowsMatchParameters.hashCode() : 0);
			_result = 31 * _result + (paymentCalculationPeriod != null ? paymentCalculationPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashflowRepresentationBuilder {" +
				"cashflowsMatchParameters=" + this.cashflowsMatchParameters + ", " +
				"paymentCalculationPeriod=" + this.paymentCalculationPeriod +
			'}';
		}
	}
}
